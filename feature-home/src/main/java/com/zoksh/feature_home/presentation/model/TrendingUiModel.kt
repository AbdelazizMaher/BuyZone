package com.zoksh.feature_home.presentation.model

data class TrendingUiModel(
    val id: String,
    val image: String,
    val name: String,
    val price: String,
    val oldPrice: String?,
    val discountPercent: Int?,
    val isFavorite: Boolean
)
