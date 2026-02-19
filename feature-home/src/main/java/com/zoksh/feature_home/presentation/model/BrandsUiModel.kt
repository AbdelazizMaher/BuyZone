package com.zoksh.feature_home.presentation.model

import androidx.compose.runtime.Immutable

@Immutable

data class BrandsUiModel(
    val id: String,
    val logoImage: String,
    val name: String,
)
