package com.zoksh.feature_search.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.feature_search.presentation.components.filter.ActiveFiltersFooter
import com.zoksh.core_common.presentation.component.ColorSelector
import com.zoksh.feature_search.presentation.components.filter.FilterGroupTitle
import com.zoksh.feature_search.presentation.components.filter.FilterHeader
import com.zoksh.feature_search.presentation.components.filter.FilterUiState
import com.zoksh.core_common.presentation.component.MultiSelectChips
import com.zoksh.feature_search.presentation.components.filter.PriceRangeSelector
import com.zoksh.core_common.presentation.component.SizeSelector
import com.zoksh.core_common.presentation.model.ColorOption

@Composable
fun FilterSection(
    uiState: FilterUiState,
    onCategoryToggle: (String) -> Unit,
    onBrandToggle: (String) -> Unit,
    onPriceChange: (ClosedFloatingPointRange<Float>) -> Unit,
    onColorSelect: (String) -> Unit,
    onSizeSelect: (String) -> Unit,
    onClearAll: () -> Unit,
    onClose: () -> Unit,
    onRemoveActiveFilter: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        FilterHeader(
            modifier = Modifier.fillMaxWidth(),
            activeCount = uiState.activeFiltersCount,
            onClearAll = onClearAll,
            onClose = onClose
        )

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            FilterGroupTitle("Category")
            MultiSelectChips(
                modifier = Modifier.fillMaxWidth(),
                options = uiState.categories,
                selectedOptions = uiState.selectedCategories,
                onOptionClick = onCategoryToggle
            )

            FilterGroupTitle("Brand")
            MultiSelectChips(
                modifier = Modifier.fillMaxWidth(),
                options = uiState.brands,
                selectedOptions = uiState.selectedBrands,
                onOptionClick = onBrandToggle
            )

            PriceRangeSelector(
                modifier = Modifier.fillMaxWidth(),
                range = uiState.priceRange,
                currencySymbol = uiState.currencySymbol,
                onRangeChange = onPriceChange
            )

            FilterGroupTitle("Color")
            ColorSelector(
                modifier = Modifier.fillMaxWidth(),
                options = uiState.colors,
                selectedId = uiState.selectedColorId,
                onSelect = onColorSelect
            )

            FilterGroupTitle("Size")
            SizeSelector(
                modifier = Modifier.fillMaxWidth(),
                options = uiState.sizes,
                selectedSize = uiState.selectedSize,
                onSelect = onSizeSelect
            )

            Spacer(Modifier.height(24.dp))
        }

        ActiveFiltersFooter(
            modifier = Modifier.fillMaxWidth(),
            uiState = uiState,
            onRemove = onRemoveActiveFilter,
            onClearAll = onClearAll
        )
    }
}


@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun FilterSectionPreview() {
    BuyZoneTheme {
        FilterSection(
            uiState = FilterUiState(
                categories = listOf(
                    "Electronics",
                    "Fashion",
                    "Home & Living",
                    "Sports",
                    "Beauty",
                    "Books"
                ),
                brands = listOf("Samsung", "Apple", "Nike", "Adidas", "Sony", "LG", "Zara", "H&M"),
                colors = listOf(
                    ColorOption("1", "Black", Color.Black),
                    ColorOption("2", "White", Color.White),
                    ColorOption("3", "Blue", Color.Blue),
                    ColorOption("4", "Red", Color.Red),
                    ColorOption("5", "Green", Color.Green),
                    ColorOption("6", "Yellow", Color.Yellow),
                    ColorOption("7", "Pink", Color(0xFFFF69B4)),
                    ColorOption("8", "Gray", Color.Gray),
                ),
                sizes = listOf("XS", "S", "M", "L", "XL", "XXL"),
                selectedCategories = setOf("Fashion", "Beauty"),
                selectedBrands = setOf("Apple"),
                selectedColorId = "4",
                selectedSize = "L",
                priceRange = 420f..1000f,
                activeFiltersCount = 6,
                currencySymbol = "€"
            ),
            onCategoryToggle = {},
            onBrandToggle = {},
            onPriceChange = {},
            onColorSelect = {},
            onSizeSelect = {},
            onClearAll = {},
            onClose = {},
            onRemoveActiveFilter = {}
        )
    }
}
