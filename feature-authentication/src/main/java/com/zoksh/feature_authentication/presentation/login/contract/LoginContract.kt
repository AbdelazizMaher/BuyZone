package com.zoksh.feature_authentication.presentation.login.contract

interface LoginContract {
    sealed interface Effect {
        data object NavigateToSignup: Effect
        data object NavigateToForgotPassword: Effect
        data object LoginSuccess : Effect
        data class ShowError(val message: String): Effect
    }
}