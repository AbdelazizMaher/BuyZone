package com.zoksh.feature_authentication.presentation.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoksh.feature_authentication.domain.model.AuthenticationCredential
import com.zoksh.feature_authentication.domain.model.AuthenticationProvider
import com.zoksh.feature_authentication.domain.model.AuthenticationResult
import com.zoksh.feature_authentication.domain.model.ValidationError
import com.zoksh.feature_authentication.domain.usecase.SignupUseCase
import com.zoksh.feature_authentication.presentation.mapper.isEmailError
import com.zoksh.feature_authentication.presentation.mapper.isNameError
import com.zoksh.feature_authentication.presentation.mapper.isPasswordError
import com.zoksh.feature_authentication.presentation.mapper.isTermsError
import com.zoksh.feature_authentication.presentation.mapper.toUiMessage
import com.zoksh.feature_authentication.presentation.model.PasswordRequirementState
import com.zoksh.feature_authentication.presentation.signup.contract.SignupContract
import com.zoksh.feature_authentication.presentation.validation.ConfirmPasswordValidationChain
import com.zoksh.feature_authentication.presentation.validation.EmailValidationChain
import com.zoksh.feature_authentication.presentation.validation.NameValidationChain
import com.zoksh.feature_authentication.presentation.validation.PasswordValidationChain
import com.zoksh.feature_authentication.presentation.validation.SignUpWithEmailValidationChain
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignupViewModel(
    val signupUseCase: SignupUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SignupContract.State())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SignupContract.Effect>()
    val event = _event.asSharedFlow()

    fun handleIntent(intent: SignupContract.Intent) {
        when (intent) {
            is SignupContract.Intent.NameChanged -> handleNameChanged(intent.name)
            SignupContract.Intent.NameFocusLost -> handleNameFocusLost()
            is SignupContract.Intent.EmailChanged -> handleEmailChanged(intent.email)
            SignupContract.Intent.EmailFocusLost -> handleEmailFocusLost()
            is SignupContract.Intent.PasswordChanged -> handlePasswordChanged(intent.password)
            SignupContract.Intent.PasswordFocusLost -> handlePasswordFocusLost()
            is SignupContract.Intent.ConfirmPasswordChanged -> handleConfirmPasswordChanged(intent.confirmPassword)
            SignupContract.Intent.ConfirmPasswordFocusLost -> handleConfirmPasswordFocusLost()
            is SignupContract.Intent.TermsAccepted -> handleTermsAccepted(intent.accepted)
            SignupContract.Intent.Login -> handleLogin()
            SignupContract.Intent.Signup -> handleSignup()
        }
    }

    private fun handleNameChanged(name: String) {
        _state.update { it.copy(name = name, nameError = null) }
    }

    private fun handleNameFocusLost() {
        val error = NameValidationChain.build(_state.value.name).handleFirstError()
        _state.update { it.copy(nameTouched = true, nameError = error?.toUiMessage()) }
    }

    private fun handleEmailChanged(email: String) {
        _state.update { it.copy(email = email, emailError = null) }
    }

    private fun handleEmailFocusLost() {
        val error = EmailValidationChain.build(_state.value.email).handleFirstError()
        _state.update { it.copy(emailTouched = true, emailError = error?.toUiMessage()) }
    }

    private fun handlePasswordChanged(password: String) {
        val errors = PasswordValidationChain.build(password).handleAllErrors()
        _state.update {
            it.copy(
                password = password,
                passwordError = null,
                passwordRequirements = PasswordRequirementState.from(errors)
            )
        }
    }

    private fun handlePasswordFocusLost() {
        val error = PasswordValidationChain.build(_state.value.password).handleFirstError()
        _state.update { it.copy(passwordTouched = true, passwordError = error?.toUiMessage()) }
    }

    private fun handleConfirmPasswordChanged(confirmPassword: String) {
        _state.update { it.copy(confirmPassword = confirmPassword, confirmPasswordError = null) }
    }

    private fun handleConfirmPasswordFocusLost() {
        val error = ConfirmPasswordValidationChain.build(
            _state.value.password,
            _state.value.confirmPassword
        ).handleFirstError()
        _state.update { it.copy(confirmPasswordTouched = true, confirmPasswordError = error?.toUiMessage()) }
    }

    private fun handleTermsAccepted(accepted: Boolean) {
        _state.update { it.copy(termsAccepted = accepted, termsAcceptedError = null) }
    }

    private fun handleLogin() {
        viewModelScope.launch {
            _event.emit(SignupContract.Effect.NavigateToLogin)
        }
    }

    private fun handleSignup() {
        viewModelScope.launch {
            _state.update { it.copy(signupClicked = true) }
            val signupResult = signupUseCase(
                credential = AuthenticationCredential.EmailAndPassword(
                    _state.value.email, _state.value.password,
                    AuthenticationProvider.EMAIL_SIGNUP
                ),
                validator = SignUpWithEmailValidationChain.build(_state.value.name, _state.value.email, _state.value.password, _state.value.confirmPassword)
            )
            when (signupResult) {
                is AuthenticationResult.Success -> { _event.emit(SignupContract.Effect.SignupSuccess(signupResult.user)) }
                is AuthenticationResult.Failure -> {  _event.emit(SignupContract.Effect.ShowError(signupResult.error.toUiMessage())) }
                AuthenticationResult.GuestAccess -> { _event.emit(SignupContract.Effect.GuestAccess) }
                is AuthenticationResult.ValidationFailed -> {
                    _state.update { state ->
                        state.copy(
                            nameError = signupResult.errors.firstOrNull { it.isNameError() }?.toUiMessage(),
                            emailError = signupResult.errors.firstOrNull { it.isEmailError() }?.toUiMessage(),
                            passwordError = signupResult.errors.firstOrNull { it.isPasswordError() }?.toUiMessage(),
                            confirmPasswordError = signupResult.errors.firstOrNull { it.isPasswordError() }?.toUiMessage(),
                            termsAcceptedError = signupResult.errors.firstOrNull { it.isTermsError() }?.toUiMessage(),

                            nameTouched = true,
                            emailTouched = true,
                            passwordTouched = true,
                            confirmPasswordTouched = true,
                        )
                    }
                }
            }
            _state.update { it.copy(signupClicked = false) }
        }
    }
}