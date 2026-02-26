package com.zoksh.feature_settings.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface SettingsDestination {
    @Serializable
    data object Settings : SettingsDestination
}
