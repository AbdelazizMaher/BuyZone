package com.zoksh.feature_onboarding.data.local

interface OnBoardingPreferences {
    fun shouldShowOnBoarding(): Boolean
    fun markOnBoardingAsSeen()
}
