package com.zoksh.feature_authentication.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoksh.feature_authentication.domain.model.AuthenticationCredential
import com.zoksh.feature_authentication.domain.model.AuthenticationProvider
import com.zoksh.feature_authentication.domain.model.AuthenticationResult
import com.zoksh.feature_authentication.domain.usecase.LoginUseCase
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract
import com.zoksh.feature_authentication.presentation.mapper.toUiMessage
import com.zoksh.feature_authentication.presentation.validation.EmailValidationChain
import com.zoksh.feature_authentication.presentation.validation.LoginWithEmailValidationChain
import com.zoksh.feature_authentication.presentation.validation.PasswordValidationChain
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginContract.State())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<LoginContract.Effect>()
    val event = _event.asSharedFlow()

    fun handleIntent(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.SignIn -> handleSignIn()
            LoginContract.Intent.SignUp -> handleSignUp()
            is LoginContract.Intent.EmailChanged -> handleEmailChanged(intent.email)
            is LoginContract.Intent.PasswordChanged -> handlePasswordChanged(intent.password)
            LoginContract.Intent.EmailFocusLost -> handleEmailFocusLost()
            LoginContract.Intent.PasswordFocusLost -> handlePasswordFocusLost()
            LoginContract.Intent.ForgotPassword -> handleForgotPassword()
            is LoginContract.Intent.RememberMe -> handleRememberMe(intent.rememberMe)
            LoginContract.Intent.FacebookLogin -> handleFacebookLogin()
            LoginContract.Intent.GoogleLogin -> handleGoogleLogin()
            LoginContract.Intent.GuestAccess -> handleGuestAccess()
        }
    }

    private fun emitEffect(effect: LoginContract.Effect) {
        viewModelScope.launch {
            _event.emit(effect)
        }
    }

    private fun handleSignIn() {
        viewModelScope.launch {
            _state.update { it.copy(loginClicked = true) }
            val loginResult = loginUseCase(
                credential = AuthenticationCredential.EmailAndPassword(
                    _state.value.email, _state.value.password, AuthenticationProvider.EMAIL_LOGIN
                ),
                validator = LoginWithEmailValidationChain.build(
                    _state.value.email,
                    _state.value.password
                )
            )
            when (loginResult) {
                is AuthenticationResult.Success -> {}
                is AuthenticationResult.Failure -> {}
                AuthenticationResult.GuestAccess -> {}
                is AuthenticationResult.ValidationFailed -> {}
            }
            _state.update { it.copy(loginClicked = false) }
        }
    }

    private fun handleSignUp() {
        emitEffect(LoginContract.Effect.NavigateToSignup)
    }

    private fun handleEmailChanged(email: String) {
        _state.update { it.copy(email = email, emailError = null) }
    }

    private fun handlePasswordChanged(password: String) {
        _state.update { it.copy(password = password, passwordError = null) }
    }

    private fun handleEmailFocusLost() {
        val error = EmailValidationChain.build(_state.value.email).handleFirstError()
        _state.update { it.copy(emailTouched = true, emailError = error?.toUiMessage()) }
    }

    private fun handlePasswordFocusLost() {
        val error = PasswordValidationChain.build(_state.value.password).handleFirstError()
        _state.update { it.copy(passwordTouched = true, passwordError = error?.toUiMessage()) }
    }

    private fun handleForgotPassword() {
        emitEffect(LoginContract.Effect.NavigateToForgotPassword)
    }

    private fun handleRememberMe(rememberMe: Boolean) {

    }

    private fun handleFacebookLogin() {

    }

    private fun handleGoogleLogin() {

    }

    private fun handleGuestAccess() {
        emitEffect(LoginContract.Effect.GuestAccess)
    }
}