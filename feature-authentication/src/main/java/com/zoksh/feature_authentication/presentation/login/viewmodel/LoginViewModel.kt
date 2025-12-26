package com.zoksh.feature_authentication.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(

): ViewModel() {

    private val _state = MutableStateFlow(LoginContract.State())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<LoginContract.Effect>()
    val event = _event.asSharedFlow()

    fun handleIntent(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.SignIn -> TODO()
            LoginContract.Intent.SignUp -> navigateToSignup()
        }
    }

    private fun emitEffect(effect: LoginContract.Effect) {
        viewModelScope.launch {
            _event.emit(effect)
        }
    }

    private fun navigateToSignup() {
        emitEffect(LoginContract.Effect.NavigateToSignup)
    }
}