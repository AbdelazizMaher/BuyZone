package com.zoksh.feature_onboarding.presentation

import kotlinx.serialization.Serializable


@Serializable
sealed interface OnBoardingDestination {
    @Serializable object onBoarding : OnBoardingDestination
}