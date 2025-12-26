package com.zoksh.feature_authentication.presentation.signup.contract

interface SignupContract {
    sealed interface Effect {
        data object NavigateBack: Effect
        data object NavigateToLogin: Effect
        data object SignupSuccess : Effect
        data class ShowError(val message: String): Effect
    }
}