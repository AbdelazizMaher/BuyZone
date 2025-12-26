package com.zoksh.feature_onboarding.presentation.contract

import com.zoksh.feature_onboarding.presentation.model.OnBoardingPageUiModel
import com.zoksh.feature_onboarding.presentation.model.OnBoardingPages

interface OnBoardingContract {

    data class State(
        val pageNo: Int = 0,
        val pages: List<OnBoardingPageUiModel> = OnBoardingPages,
    ) {
        val currentPageData: OnBoardingPageUiModel
            get() = pages[pageNo]
    }

    sealed interface Intent {
        data object Skip : Intent
        data object Next : Intent
        data object GetStarted : Intent
        data class PageChanged(val pageIndex: Int): Intent
    }

    sealed interface Effect {
        data object GetStarted: Effect
    }
}

