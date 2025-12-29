package com.zoksh.feature_authentication.domain.model

sealed interface ValidationError {
    object EmptyEmail : ValidationError
    object EmptyPassword : ValidationError
    object EmptyName : ValidationError
    object InvalidEmail : ValidationError
    object InvalidName : ValidationError
    object PasswordTooShort : ValidationError
    object PasswordNoUpperCase : ValidationError
    object PasswordNoLowerCase : ValidationError
    object PasswordNoNumber : ValidationError
    object PasswordNoSpecialChar : ValidationError
    object PasswordNoMatching : ValidationError
}