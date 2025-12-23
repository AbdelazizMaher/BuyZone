package com.zoksh.feature_onboarding.domain.repository

interface OnBoardingRepository {
    fun shouldShowOnBoarding(): Boolean
    fun markOnBoardingAsSeen()
}
