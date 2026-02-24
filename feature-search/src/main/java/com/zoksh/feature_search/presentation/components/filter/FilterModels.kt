package com.zoksh.feature_search.presentation.components.filter

import androidx.compose.runtime.Immutable
import com.zoksh.core_common.presentation.model.ColorOption

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
