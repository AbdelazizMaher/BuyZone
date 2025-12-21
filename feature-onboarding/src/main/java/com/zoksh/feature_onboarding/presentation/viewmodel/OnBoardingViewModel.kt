package com.zoksh.feature_onboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.zoksh.feature_onboarding.presentation.contract.OnBoardingContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnBoardingViewModel(

): ViewModel() {
    private val _state = MutableStateFlow(OnBoardingContract.State())
    val state = _state.asStateFlow()

    fun handleIntent(intent: OnBoardingContract.Intent) {
        when (intent) {
            OnBoardingContract.Intent.GetStarted -> TODO()
            OnBoardingContract.Intent.Next -> TODO()
            OnBoardingContract.Intent.Skip -> TODO()
        }
    }
}