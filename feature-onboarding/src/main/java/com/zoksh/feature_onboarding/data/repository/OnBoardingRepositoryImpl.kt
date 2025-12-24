package com.zoksh.feature_onboarding.data.repository

import com.zoksh.feature_onboarding.data.local.OnBoardingPreferences
import com.zoksh.feature_onboarding.domain.repository.OnBoardingRepository

class OnBoardingRepositoryImpl(
    private val onBoardingPreferences: OnBoardingPreferences
): OnBoardingRepository {
    override fun shouldShowOnBoarding(): Boolean {
        return onBoardingPreferences.shouldShowOnBoarding()
    }

    override fun markOnBoardingAsSeen() {
        return onBoardingPreferences.markOnBoardingAsSeen()
    }
}