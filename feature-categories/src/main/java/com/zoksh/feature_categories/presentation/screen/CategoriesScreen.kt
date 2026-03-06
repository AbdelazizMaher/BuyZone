package com.zoksh.feature_categories.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_common.R
import com.zoksh.core_common.presentation.component.CategoriesSection
import com.zoksh.core_common.presentation.component.ProductCard
import com.zoksh.core_common.presentation.model.CategoryUiModel
import com.zoksh.core_common.presentation.ui_state.UiState
import com.zoksh.core_ui.components.AppHeader
import com.zoksh.core_ui.components.AppSearchBar
import com.zoksh.core_ui.components.EmptyStateComponent
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.feature_categories.presentation.components.CategoriesRowShimmer
import com.zoksh.feature_categories.presentation.components.DependentContentShimmer
import com.zoksh.feature_categories.presentation.components.ProductTypeFilter
import com.zoksh.feature_categories.presentation.contract.CategoriesContract

@Composable
fun CategoriesScreen(
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
                when (val catState = state.categories) {
                    is UiState.Success -> {
                        CategoriesSection(
                            state = catState,
                            selectedCategoryId = state.selectedCategoryId,
                            onCategoryClick = {
                                onIntent(
                                    CategoriesContract.Intent.OnCategorySelect(
                                        it
                                    )
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    is UiState.Loading -> {
                        CategoriesRowShimmer()
                    }

                    else -> {}
                }
            }

            if (state.selectedCategoryId != null) {
                if (state.products is UiState.Loading) {
                    item(span = { GridItemSpan(2) }) {
                        DependentContentShimmer()
                    }
                } else {
                    if (state.productTypes is UiState.Success) {
                        item(span = { GridItemSpan(2) }, key = "product_types_section") {
                            ProductTypeFilter(
                                state = state.productTypes,
                                selectedType = state.selectedProductType,
                                onTypeSelect = {
                                    onIntent(
                                        CategoriesContract.Intent.OnProductTypeSelect(
                                            it
                                        )
                                    )
                                }
                            )
                        }
                    }

                    when (val productsState = state.products) {
                        is UiState.Success -> {
                            items(productsState.data, key = { it.id }) { product ->
                                ProductCard(
                                    product = product,
                                    onClick = { onIntent(CategoriesContract.Intent.OnProductClick(it)) },
                                    onFavoriteClick = {}
                                )
                            }
                        }

                        is UiState.Empty -> {
                            item(span = { GridItemSpan(2) }) {
                                EmptyStateComponent(
                                    title = "No products found",
                                    description = "There are no products available in this category yet."
                                )
                            }
                        }

                        is UiState.Error -> {
                            item(span = { GridItemSpan(2) }) {
                                EmptyStateComponent(
                                    title = "Something went wrong",
                                    description = productsState.message
                                        ?: "Failed to load products.",
                                    actionText = "Retry",
                                    onActionClick = { onIntent(CategoriesContract.Intent.LoadInitialData) }
                                )
                            }
                        }

                        else -> {}
                    }
                }
            } else {
                item(span = { GridItemSpan(2) }) {
                    Box(modifier = Modifier.padding(top = 40.dp)) {
                        EmptyStateComponent(
                            title = "Select a category",
                            description = "Choose a category above to start browsing our curated collection."
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Initial State (No Selection)")
@Composable
private fun CategoriesScreenInitialPreview() {
    BuyZoneTheme {
        CategoriesScreen(
            state = CategoriesContract.State(
                categories = UiState.Success(
                    listOf(
                        CategoryUiModel(id = "1", R.drawable.males_category, "Male", "👨"),
                        CategoryUiModel(id = "2", R.drawable.women_category, "Female", "👩")
                    )
                ),
                selectedCategoryId = null,
                products = UiState.Empty
            ),
            onIntent = {},
            innerPadding = PaddingValues(0.dp)
        )
    }
}

@Preview(showBackground = true, name = "Loading Products")
@Composable
private fun CategoriesScreenLoadingPreview() {
    BuyZoneTheme {
        CategoriesScreen(
            state = CategoriesContract.State(
                categories = UiState.Success(
                    listOf(
                        CategoryUiModel(id = "1", R.drawable.males_category, "Male", "👨")
                    )
                ),
                selectedCategoryId = "1",
                products = UiState.Loading
            ),
            onIntent = {},
            innerPadding = PaddingValues(0.dp)
        )
    }
}
