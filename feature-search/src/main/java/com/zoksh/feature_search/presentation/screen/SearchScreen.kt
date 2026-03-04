package com.zoksh.feature_search.presentation.screen

import android.content.res.Configuration
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_common.presentation.component.ProductCard
import com.zoksh.core_common.presentation.model.ColorOption
import com.zoksh.core_common.presentation.model.ProductUiModel
import com.zoksh.core_ui.components.AppHeader
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.feature_search.presentation.components.FilterSection
import com.zoksh.feature_search.presentation.components.SearchBarSection
import com.zoksh.feature_search.presentation.components.SearchResultShimmer
import com.zoksh.feature_search.presentation.contract.SearchContract

@Composable
fun SearchScreen(
    state: SearchContract.State,
    onIntent: (SearchContract.Intent) -> Unit,
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            )
    ) {
        AppHeader(
            modifier = Modifier.fillMaxWidth(),
            title = "Search Products",
            onBackClick = { onIntent(SearchContract.Intent.NavigateBack) }
        )

        SearchBarSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            value = state.query,
            onValueChange = { onIntent(SearchContract.Intent.OnQueryChange(it)) },
            hint = "Search products...",
            onClearQuery = { onIntent(SearchContract.Intent.OnQueryChange("")) },
            onFilterClick = { onIntent(SearchContract.Intent.ToggleFilter) }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            if (state.products.isEmpty() && state.query.isEmpty()) {
                SearchResultShimmer(modifier = Modifier.fillMaxSize())
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.products, key = { it.id }) { product ->
                        ProductCard(
                            product = product,
                            onClick = { onIntent(SearchContract.Intent.OnProductClick(it)) },
                            onFavoriteClick = { }
                        )
                    }
                }
            }

            androidx.compose.animation.AnimatedVisibility(
                visible = state.isFilterVisible,
                enter = slideInVertically { -it } + expandVertically(expandFrom = Alignment.Top) + fadeIn(),
                exit = slideOutVertically { -it } + shrinkVertically(shrinkTowards = Alignment.Top) + fadeOut()
            ) {
                FilterSection(
                    modifier = Modifier.fillMaxSize(),
                    uiState = state.filterState,
                    onCategoryToggle = { onIntent(SearchContract.Intent.SelectCategory(it)) },
                    onBrandToggle = { onIntent(SearchContract.Intent.SelectBrand(it)) },
                    onPriceChange = { onIntent(SearchContract.Intent.OnPriceChange(it)) },
                    onColorSelect = { onIntent(SearchContract.Intent.SelectColor(it)) },
                    onSizeSelect = { onIntent(SearchContract.Intent.SelectSize(it)) },
                    onClearAll = { onIntent(SearchContract.Intent.ClearFilters) },
                    onClose = { onIntent(SearchContract.Intent.ToggleFilter) },
                    onRemoveActiveFilter = { onIntent(SearchContract.Intent.RemoveFilter(it)) }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun SearchScreenPreview() {
    val mockProducts = listOf(
        ProductUiModel(
            "1",
            "https://picsum.photos/300/300?random=1",
            "Adidas Ultraboost",
            "$150",
            "$200",
            20,
            false
        ),
        ProductUiModel(
            "2", "https://picsum.photos/300/300?random=2", "Nike Air Max", "$150", "$200", 25, true
        )
    )

    BuyZoneTheme {
        SearchScreen(
            state = SearchContract.State(
                products = mockProducts,
                filterState = com.zoksh.feature_search.presentation.components.filter.FilterUiState(
                    categories = listOf("Fashion", "Electronics"),
                    brands = listOf("Apple", "Nike"),
                    colors = listOf(ColorOption("1", "Black", Color.Black)),
                    sizes = listOf("S", "M", "L")
                )
            ),
            onIntent = {},
            innerPadding = PaddingValues(0.dp)
        )
    }
}
