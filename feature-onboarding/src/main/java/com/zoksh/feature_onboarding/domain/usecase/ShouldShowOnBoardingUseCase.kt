package com.zoksh.feature_onboarding.domain.usecase

import com.zoksh.feature_onboarding.domain.repository.OnBoardingRepository

class ShouldShowOnBoardingUseCase(
    private val onBoardingRepository: OnBoardingRepository
) {
    operator fun invoke() {
        onBoardingRepository.shouldShowOnBoarding()
    }
}