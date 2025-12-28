package com.zoksh.feature_authentication.domain.model

sealed interface AuthenticationResult {
    object Success : AuthenticationResult
    data class AuthenticationError(val error: ValidationError) : AuthenticationResult
    object AuthenticationFailed : AuthenticationResult
    object UnKnownError : AuthenticationResult
}
