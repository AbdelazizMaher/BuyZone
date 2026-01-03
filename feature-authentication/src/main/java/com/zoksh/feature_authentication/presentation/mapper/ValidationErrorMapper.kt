package com.zoksh.feature_authentication.presentation.mapper

import com.zoksh.feature_authentication.domain.model.ValidationError

fun ValidationError.toUiMessage(): String {
    return when (this) {
        ValidationError.EmptyEmail -> "Email cannot be empty"
        ValidationError.EmptyPassword -> "Password cannot be empty"
        ValidationError.EmptyName -> "Name cannot be empty"
        ValidationError.InvalidEmail -> "Invalid email"
        ValidationError.InvalidName -> "Invalid name"
        ValidationError.PasswordTooShort -> "Password must be at least 8 characters"
        ValidationError.PasswordNoUpperCase -> "Password must contain at least one uppercase letter"
        ValidationError.PasswordNoLowerCase -> "Password must contain at least one lowercase letter"
        ValidationError.PasswordNoNumber -> "Password must contain at least one number"
        ValidationError.PasswordNoSpecialChar -> "Password must contain at least one special character"
        ValidationError.PasswordNoMatching -> "Passwords do not match"
    }
}