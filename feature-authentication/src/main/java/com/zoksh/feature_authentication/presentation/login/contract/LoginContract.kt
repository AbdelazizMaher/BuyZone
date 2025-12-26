package com.zoksh.feature_authentication.presentation.login.contract

interface LoginContract {
    data class State(
        val email: String = "",
        val password: String = "",
        val isChecked: Boolean = false
    )

    sealed interface Intent {
        data class SignIn(val email: String, val password: String) : Intent
        data object SignUp: Intent
    }
    sealed interface Effect {
        data object NavigateToSignup: Effect
        data object NavigateToForgotPassword: Effect
        data object LoginSuccess : Effect
        data class ShowError(val message: String): Effect
    }
}