package com.zoksh.feature_cart.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface CartDestination {
    @Serializable
    data object Cart : CartDestination
}
