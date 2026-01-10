package com.zoksh.feature_home.presentation.model

data class TrendingUiModel(
    val image: String,
    val name: String,
    val price: String,
    val onClick: () -> Unit,
)
