package com.zoksh.feature_splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoksh.feature_splash.presentation.contract.SplashContract
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _state = MutableStateFlow(SplashContract.State)
    val state: StateFlow<SplashContract.State> = _state.asStateFlow()

    private val _effect = Channel<SplashContract.Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        handleIntent(SplashContract.Intent.Initialize)
    }

    fun handleIntent(intent: SplashContract.Intent) {
        when (intent) {
            SplashContract.Intent.Initialize -> startTimer()
        }
    }

    private fun startTimer() {
        viewModelScope.launch {
            delay(2000)
            _effect.send(SplashContract.Effect.OnFinished)
        }
    }
}
