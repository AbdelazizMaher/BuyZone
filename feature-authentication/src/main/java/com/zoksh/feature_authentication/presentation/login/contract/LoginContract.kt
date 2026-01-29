package com.zoksh.feature_authentication.presentation.login.contract

import com.zoksh.core_ui.snackbar.model.AppMessage
import com.zoksh.feature_authentication.domain.model.User

interface LoginContract {
    data class State(
        val email: String = "",
        val emailTouched: Boolean = false,
        val emailError: String? = null,

        val password: String = "",
        val passwordTouched: Boolean = false,
        val passwordError: String? = null,

        val rememberMe: Boolean = false,

        val loginClicked: Boolean = false,
        val submitAttempted: Boolean = false
    )

    sealed interface Intent {
        data class EmailChanged(val email: String) : Intent
        data class PasswordChanged(val password: String) : Intent
        data class RememberMe(val rememberMe: Boolean) : Intent
        object EmailFocusLost : Intent
        object PasswordFocusLost : Intent

        data object GuestAccess : Intent
        data object ForgotPassword : Intent
        data object FacebookLogin : Intent
        data class FacebookAuthSuccess(val token: String) : Intent
        data class FacebookAuthFailure(val error: String) : Intent
        data object GoogleLogin : Intent
        data class GoogleAuthSuccess(val idToken: String) : Intent
        data class GoogleAuthFailure(val error: String) : Intent
        data object SignIn : Intent
        data object SignUp: Intent
    }
    sealed interface Effect {
        data object NavigateToSignup: Effect
        data object NavigateToForgotPassword: Effect
        data object StartGoogleAuth: Effect
        data object StartFacebookAuth: Effect
        data class LoginSuccess(val user: User) : Effect
        data object GuestAccess : Effect
        data class ShowError(val message: AppMessage): Effect
    }
}