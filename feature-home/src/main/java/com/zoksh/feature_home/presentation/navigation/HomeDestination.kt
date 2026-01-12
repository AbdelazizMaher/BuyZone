package com.zoksh.feature_home.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeDestination {
    @Serializable
    data object Home : HomeDestination
}