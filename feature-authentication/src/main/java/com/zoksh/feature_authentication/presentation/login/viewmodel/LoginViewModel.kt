package com.zoksh.feature_authentication.presentation.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoksh.feature_authentication.domain.model.AuthenticationCredential
import com.zoksh.feature_authentication.domain.model.AuthenticationProvider
import com.zoksh.feature_authentication.domain.model.AuthenticationResult
import com.zoksh.feature_authentication.domain.usecase.LoginUseCase
import com.zoksh.feature_authentication.domain.validation.ValidationHandler
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract
import com.zoksh.feature_authentication.presentation.mapper.isEmailError
import com.zoksh.feature_authentication.presentation.mapper.isPasswordError
import com.zoksh.feature_authentication.presentation.mapper.toUiMessage
import com.zoksh.feature_authentication.presentation.validation.EmailValidationChain
import com.zoksh.feature_authentication.presentation.validation.LoginWithEmailValidationChain
import com.zoksh.feature_authentication.presentation.validation.PasswordValidationChain
import com.zoksh.feature_authentication.presentation.validation.SocialValidationChain
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
            is LoginContract.Intent.SignIn -> handleSignInWithEmailAndPassword()
            LoginContract.Intent.SignUp -> handleSignUp()
            is LoginContract.Intent.EmailChanged -> handleEmailChanged(intent.email)
            is LoginContract.Intent.PasswordChanged -> handlePasswordChanged(intent.password)
            LoginContract.Intent.EmailFocusLost -> handleEmailFocusLost()
            LoginContract.Intent.PasswordFocusLost -> handlePasswordFocusLost()
            LoginContract.Intent.ForgotPassword -> handleForgotPassword()
            is LoginContract.Intent.RememberMe -> handleRememberMe(intent.rememberMe)
            LoginContract.Intent.FacebookLogin -> handleFacebookLogin()
            is LoginContract.Intent.FacebookAuthFailure -> handleFacebookAuthFailure(intent.error)
            is LoginContract.Intent.FacebookAuthSuccess -> handleFacebookAuthSuccess(intent.token)
            LoginContract.Intent.GoogleLogin -> handleGoogleLogin()
            is LoginContract.Intent.GoogleAuthFailure -> handleGoogleAuthFailure(intent.error)
            is LoginContract.Intent.GoogleAuthSuccess -> handleGoogleAuthSuccess(intent.idToken)
            LoginContract.Intent.GuestAccess -> handleGuestAccess()
        }
    }

    private fun handleSignUp() {
        viewModelScope.launch {
           _event.emit(LoginContract.Effect.NavigateToSignup)
        }
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
        viewModelScope.launch {
            _event.emit(LoginContract.Effect.NavigateToForgotPassword)
        }
    }

    private fun handleRememberMe(rememberMe: Boolean) {
        _state.update { it.copy(rememberMe = rememberMe) }
    }

    private fun handleSignIn(validator: ValidationHandler,credential: AuthenticationCredential) {
        viewModelScope.launch {
            _state.update { it.copy(loginClicked = true, submitAttempted = true) }
            val loginResult = loginUseCase(
                credential = credential,
                validator = validator
            )
            when (loginResult) {
                is AuthenticationResult.Success -> { _event.emit(LoginContract.Effect.LoginSuccess(loginResult.user)) }
                is AuthenticationResult.Failure -> { _event.emit(LoginContract.Effect.ShowError(loginResult.error.toUiMessage())) }
                AuthenticationResult.GuestAccess -> { _event.emit(LoginContract.Effect.GuestAccess) }
                is AuthenticationResult.ValidationFailed -> { _state.update { state ->
                    state.copy(
                        emailError = loginResult.errors.firstOrNull { it.isEmailError() }?.toUiMessage(),
                        passwordError = loginResult.errors.firstOrNull { it.isPasswordError() }?.toUiMessage(),

                        emailTouched = true,
                        passwordTouched = true,
                    )
                }}
            }
            _state.update { it.copy(loginClicked = false) }
        }
    }

    private fun handleSignInWithEmailAndPassword() {
        val validator = LoginWithEmailValidationChain.build(_state.value.email, _state.value.password)
        val credential = AuthenticationCredential.EmailAndPassword(
            _state.value.email, _state.value.password,AuthenticationProvider.EMAIL_LOGIN
        )
        handleSignIn(validator,credential)
    }

    private fun handleFacebookLogin() {
        viewModelScope.launch {
            _event.emit(LoginContract.Effect.StartFacebookAuth)
        }
    }

    private fun handleFacebookAuthSuccess(token: String) {
        val validator = SocialValidationChain.build()
        val credential = AuthenticationCredential.Social(token = token, provider = AuthenticationProvider.FACEBOOK)
        handleSignIn(validator, credential)
    }

    private fun handleFacebookAuthFailure(error: String) {
        viewModelScope.launch {
            _event.emit(LoginContract.Effect.ShowError(error))
        }
    }

    private fun handleGoogleLogin() {
        viewModelScope.launch {
            _event.emit(LoginContract.Effect.StartGoogleAuth)
        }
    }

    private fun handleGoogleAuthSuccess(idToken: String) {
        val validator = SocialValidationChain.build()
        val credential = AuthenticationCredential.Social(token = idToken, provider = AuthenticationProvider.GOOGLE)
        handleSignIn(validator, credential)
    }

    private fun handleGoogleAuthFailure(error: String) {
        viewModelScope.launch {
            _event.emit(LoginContract.Effect.ShowError(error))
        }
    }

    private fun handleGuestAccess() {
        viewModelScope.launch {
            _event.emit(LoginContract.Effect.GuestAccess)
        }
    }
}