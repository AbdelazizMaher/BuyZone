package com.zoksh.feature_splash.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface SplashDestination {
    @Serializable
    data object Splash : SplashDestination
}
