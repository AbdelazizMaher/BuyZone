package com.zoksh.feature_authentication.presentation.login.contract

interface LoginContract {
    data class State(
        val email: String = "",
        val emailTouched: Boolean = false,
        val emailError: String? = null,

        val password: String = "",
        val passwordTouched: Boolean = false,
        val passwordError: String? = null,

        val rememberMe: Boolean = false,

        val loginClicked: Boolean = false
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
        data object GoogleLogin : Intent
        data object SignIn : Intent
        data object SignUp: Intent
    }
    sealed interface Effect {
        data object NavigateToSignup: Effect
        data object NavigateToForgotPassword: Effect
        data object LoginSuccess : Effect
        data object GuestAccess : Effect
        data class ShowError(val message: String): Effect
    }
}