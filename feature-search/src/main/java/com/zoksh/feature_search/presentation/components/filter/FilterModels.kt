package com.zoksh.feature_search.presentation.components.filter

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class FilterUiState(
    val categories: List<String> = emptyList(),
    val brands: List<String> = emptyList(),
    val colors: List<ColorOption> = emptyList(),
    val sizes: List<String> = emptyList(),
    val selectedCategories: Set<String> = emptySet(),
    val selectedBrands: Set<String> = emptySet(),
    val selectedColorId: String? = null,
    val selectedSize: String? = null,
    val priceRange: ClosedFloatingPointRange<Float> = 0f..1000f,
    val activeFiltersCount: Int = 0,
    val currencySymbol: String = "$"
)

data class ColorOption(val id: String, val name: String, val color: Color)
