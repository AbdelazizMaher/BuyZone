package com.zoksh.feature_onboarding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoksh.feature_onboarding.domain.usecase.MarkOnBoardingAsSeenUseCase
import com.zoksh.feature_onboarding.presentation.contract.OnBoardingContract
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val markOnBoardingAsSeenUseCase: MarkOnBoardingAsSeenUseCase
): ViewModel() {
    private val _state = MutableStateFlow(OnBoardingContract.State())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<OnBoardingContract.Effect>()
    val event = _event.asSharedFlow()


    fun handleIntent(intent: OnBoardingContract.Intent) {
        when (intent) {
            OnBoardingContract.Intent.GetStarted -> getStarted()
            OnBoardingContract.Intent.Next -> nextPage()
            OnBoardingContract.Intent.Skip -> skip()
            is OnBoardingContract.Intent.PageChanged -> pageChanged(intent.pageIndex)
        }
    }

    private fun emitEffect(effect: OnBoardingContract.Effect) {
        viewModelScope.launch {
            _event.emit(effect)
        }
    }

    private fun getStarted() {
        markOnBoardingAsSeenUseCase()
        emitEffect(OnBoardingContract.Effect.GetStarted)
    }

    private fun nextPage() {
        _state.update {
            it.copy(
                pageNo = it.pageNo + 1,
            )
        }
    }

    private fun skip() {
        _state.update {
            it.copy(
                pageNo = it.pages.size - 1,
            )
        }
    }

    private fun pageChanged(pageIndex: Int) {
        _state.update {
            it.copy(
                pageNo = pageIndex,
            )
        }
    }
}