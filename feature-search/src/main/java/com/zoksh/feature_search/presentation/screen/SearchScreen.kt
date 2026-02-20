package com.zoksh.feature_search.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zoksh.core_ui.components.AppHeader
import com.zoksh.feature_search.presentation.components.FilterSection
import com.zoksh.feature_search.presentation.components.SearchBarSection
import com.zoksh.feature_search.presentation.components.filter.ColorOption
import com.zoksh.feature_search.presentation.components.filter.FilterUiState

@Composable
fun SearchScreen(
    innerPadding: PaddingValues
) {
    var isFilterVisible by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding(), bottom = innerPadding.calculateBottomPadding())
    ) {
        AppHeader(
            modifier = Modifier.fillMaxWidth(),
            title = "Search Products",
            onBackClick = {  }
        )

        SearchBarSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            value = query,
            onValueChange = { query = it },
            hint = "Search products...",
            onClearQuery = { query = "" },
            onFilterClick = { isFilterVisible = !isFilterVisible }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

            }

            androidx.compose.animation.AnimatedVisibility(
                visible = isFilterVisible,
                enter = slideInVertically { -it } + expandVertically(expandFrom = Alignment.Top) + fadeIn(),
                exit = slideOutVertically { -it } + shrinkVertically(shrinkTowards = Alignment.Top) + fadeOut()
            ) {
                FilterSection(
                    modifier = Modifier.fillMaxSize(),
                    uiState = FilterUiState(
                        categories = listOf("Electronics", "Fashion", "Home & Living", "Sports", "Beauty", "Books", "Toys", "Fitness"),
                        brands = listOf("Apple", "Samsung", "Nike", "Adidas", "Sony", "LG", "Zara", "H&M", "Puma", "Reebok"),
                        colors = listOf(
                            ColorOption("1", "Black", Color.Black),
                            ColorOption("2", "White", Color.White),
                            ColorOption("3", "Blue", Color.Blue),
                            ColorOption("4", "Red", Color.Red),
                            ColorOption("5", "Green", Color.Green),
                            ColorOption("6", "Yellow", Color.Yellow),
                            ColorOption("7", "Pink", Color(0xFFFF69B4)),
                            ColorOption("8", "Gray", Color.Gray)
                        ),
                        sizes = listOf("XS", "S", "M", "L", "XL", "XXL", "3XL"),
                        selectedCategories = setOf("Fashion", "Beauty"),
                        selectedBrands = setOf("Apple", "Nike"),
                        selectedColorId = "4", // Red
                        selectedSize = "L",
                        priceRange = 420f..1000f,
                        activeFiltersCount = 6,
                        currencySymbol = "$"
                    ),
                    onCategoryToggle = {},
                    onBrandToggle = {},
                    onPriceChange = {},
                    onColorSelect = {},
                    onSizeSelect = {},
                    onClearAll = {  },
                    onClose = { isFilterVisible = false },
                    onRemoveActiveFilter = {}
                )
            }
        }
    }
}