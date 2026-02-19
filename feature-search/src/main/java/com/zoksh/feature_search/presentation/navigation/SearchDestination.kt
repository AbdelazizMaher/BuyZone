package com.zoksh.feature_search.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface SearchDestination {
    @Serializable
    data object Search : SearchDestination
}