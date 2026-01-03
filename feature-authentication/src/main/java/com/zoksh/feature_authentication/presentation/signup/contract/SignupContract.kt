package com.zoksh.feature_authentication.presentation.signup.contract

import com.zoksh.feature_authentication.domain.model.User
import com.zoksh.feature_authentication.presentation.model.PasswordRequirementState

interface SignupContract {
    data class State(
        val name: String = "",
        val nameTouched: Boolean = false,
        val nameError: String? = null,

        val email: String = "",
        val emailTouched: Boolean = false,
        val emailError: String? = null,

        val password: String = "",
        val passwordTouched: Boolean = false,
        val passwordError: String? = null,

        val confirmPassword: String = "",
        val confirmPasswordTouched: Boolean = false,
        val confirmPasswordError: String? = null,

        val passwordRequirements: PasswordRequirementState = PasswordRequirementState(),

        val termsAccepted: Boolean = false,
        val termsAcceptedError: String? = null,

        val signupClicked: Boolean = false
    )

    sealed interface Intent {
        data class NameChanged(val name: String) : Intent
        data class EmailChanged(val email: String) : Intent
        data class PasswordChanged(val password: String) : Intent
        data class ConfirmPasswordChanged(val confirmPassword: String) : Intent

        object NameFocusLost : Intent
        object EmailFocusLost : Intent
        object PasswordFocusLost : Intent
        object ConfirmPasswordFocusLost : Intent

        data class TermsAccepted(val accepted: Boolean) : Intent
        data object Signup : Intent
        data object Login: Intent
    }

    sealed interface Effect {
        data object NavigateBack: Effect
        data object NavigateToLogin: Effect
        data object GuestAccess: Effect
        data class SignupSuccess(val user: User) : Effect
        data class ShowError(val message: String): Effect
    }
}