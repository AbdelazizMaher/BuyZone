package com.zoksh.core_common.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class ProductUiModel(
    val id: String,
    val image: String,
    val name: String,
    val price: String,
    val oldPrice: String?,
    val discountPercent: Int?,
    val isFavorite: Boolean
)