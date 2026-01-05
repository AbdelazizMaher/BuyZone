package com.zoksh.feature_authentication.presentation.model

import com.zoksh.feature_authentication.domain.model.ValidationError

data class PasswordRequirementState(
    val minLength: Boolean = false,
    val upperCase: Boolean = false,
    val lowerCase: Boolean = false,
    val number: Boolean = false,
    val specialChar: Boolean = false
) {
    companion object {
        fun from(errors: List<ValidationError>): PasswordRequirementState {
            return PasswordRequirementState(
                minLength = errors.none { it == ValidationError.PasswordTooShort },
                upperCase = errors.none { it == ValidationError.PasswordNoUpperCase },
                lowerCase = errors.none { it == ValidationError.PasswordNoLowerCase },
                number = errors.none { it == ValidationError.PasswordNoNumber },
                specialChar = errors.none { it == ValidationError.PasswordNoSpecialChar }
            )
        }
    }
}
