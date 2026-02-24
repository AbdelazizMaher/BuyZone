package com.zoksh.feature_categories.presentation.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zoksh.core_common.R
import com.zoksh.core_common.presentation.component.CategoriesSection
import com.zoksh.core_common.presentation.component.ProductCard
import com.zoksh.core_common.presentation.model.CategoryUiModel
import com.zoksh.core_common.presentation.model.ProductUiModel
import com.zoksh.core_common.presentation.ui_state.UiState
import com.zoksh.core_ui.components.AppHeader
import com.zoksh.core_ui.components.AppSearchBar
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.feature_categories.presentation.components.ProductTypeFilter
import com.zoksh.feature_categories.presentation.contract.CategoriesContract
import com.zoksh.feature_categories.presentation.viewmodel.CategoriesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CategoriesScreen(
    innerPadding: PaddingValues,
    viewModel: CategoriesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CategoriesContent(
        state = state,
        onIntent = viewModel::handleIntent,
        innerPadding = innerPadding
    )
}

@Composable
private fun CategoriesContent(
    state: CategoriesContract.State,
    onIntent: (CategoriesContract.Intent) -> Unit,
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = innerPadding.calculateTopPadding())
    ) {
        AppHeader(
            title = "Categories",
            modifier = Modifier.fillMaxWidth(),
            onBackClick = { onIntent(CategoriesContract.Intent.OnBackClick) }
        )

        AppSearchBar(
            value = state.searchQuery,
            onValueChange = { onIntent(CategoriesContract.Intent.OnSearchQueryChange(it)) },
            hint = "Search in categories...",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                bottom = innerPadding.calculateBottomPadding() + 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(span = { GridItemSpan(2) }, key = "categories_section") {
                CategoriesSection(
                    state = state.categories,
                    onCategoryClick = { onIntent(CategoriesContract.Intent.OnCategorySelect(it)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item(span = { GridItemSpan(2) }, key = "product_types_section") {
                ProductTypeFilter(
                    state = state.productTypes,
                    selectedType = state.selectedProductType,
                    onTypeSelect = { onIntent(CategoriesContract.Intent.OnProductTypeSelect(it)) }
                )
            }

            when (val productsState = state.products) {
                is UiState.Success -> {
                    items(productsState.data, key = { it.id }) { product ->
                        ProductCard(
                            product = product,
                            onClick = { onIntent(CategoriesContract.Intent.OnProductClick(it)) },
                            onFavoriteClick = {  }
                        )
                    }
                }
                is UiState.Loading -> {
                    
                }
                else -> {}
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
private fun CategoriesScreenPreview() {
    val mockCategories = listOf(
        CategoryUiModel(id = "1", R.drawable.males_category, "Male", "👨"),
        CategoryUiModel(id = "2", R.drawable.women_category, "Female", "👩"),
        CategoryUiModel(id = "3", R.drawable.kids_category, "Kids", "👶"),
    )
    val mockProducts = listOf(
        ProductUiModel("1", "https://picsum.photos/300/300?random=1", "Adidas Ultraboost", "$150", "$200", 20, false),
        ProductUiModel("2", "https://picsum.photos/300/300?random=2", "Nike Air Max", "$150", "$200", 25, true),
    )

    BuyZoneTheme {
        CategoriesContent(
            state = CategoriesContract.State(
                categories = UiState.Success(mockCategories),
                productTypes = UiState.Success(listOf("All", "Shoes", "Clothing", "Accessories")),
                selectedProductType = "All",
                products = UiState.Success(mockProducts)
            ),
            onIntent = {},
            innerPadding = PaddingValues(0.dp)
        )
    }
}
