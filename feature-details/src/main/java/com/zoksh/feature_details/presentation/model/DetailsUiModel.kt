package com.zoksh.feature_details.presentation.model

import androidx.compose.runtime.Immutable
import com.zoksh.core_common.presentation.model.ColorOption

@Immutable
data class DetailsUiModel(
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val description: String = "",
    val price: String = "",
    val rating: Double = 0.0,
    val images: List<String> = emptyList(),
    val sizes: List<String> = emptyList(),
    val colors: List<ColorOption> = emptyList(),
    val selectedSize: String? = null,
    val selectedColorId: String? = null
)
