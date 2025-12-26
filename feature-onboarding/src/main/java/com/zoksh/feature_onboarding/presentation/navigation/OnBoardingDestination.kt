package com.zoksh.feature_onboarding.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface OnBoardingDestination {
    @Serializable
    object OnBoarding : OnBoardingDestination
}