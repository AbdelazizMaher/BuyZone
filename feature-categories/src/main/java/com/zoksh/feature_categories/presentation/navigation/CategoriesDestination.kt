package com.zoksh.feature_categories.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface CategoriesDestination {
    @Serializable
    data object Categories : CategoriesDestination
}
