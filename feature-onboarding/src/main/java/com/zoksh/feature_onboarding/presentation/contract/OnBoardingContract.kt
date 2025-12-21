package com.zoksh.feature_onboarding.presentation.contract

import com.zoksh.feature_onboarding.presentation.model.OnBoardingPageUiModel

interface OnBoardingContract {

    data class State(
        val pageNo: Int = 0,
        val pages: List<OnBoardingPageUiModel> = emptyList(),
    ) {
        val currentPageData: OnBoardingPageUiModel
            get() = pages[pageNo]
    }

    sealed interface Intent {
        data object Skip : Intent
        data object Next : Intent
        data object GetStarted : Intent
    }

    sealed interface Effect {
        data object NavigateToApp: Effect
    }
}

