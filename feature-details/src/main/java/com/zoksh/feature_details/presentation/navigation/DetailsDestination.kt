package com.zoksh.feature_details.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface DetailsDestination {
    @Serializable
    data class Details(val productId: String) : DetailsDestination
}
