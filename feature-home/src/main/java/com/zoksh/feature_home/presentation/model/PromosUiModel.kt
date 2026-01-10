package com.zoksh.feature_home.presentation.model

data class PromosUiModel(
    val promos: List<String>,
    val onClick: () -> Unit
)
