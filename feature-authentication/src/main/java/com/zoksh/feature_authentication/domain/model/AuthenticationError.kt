package com.zoksh.feature_authentication.domain.model

sealed interface AuthenticationError {
    object InvalidCredentials : AuthenticationError
    object UserAlreadyExists : AuthenticationError
    object UserNotRegistered : AuthenticationError
    object ShopLinkFailed : AuthenticationError
    object NetworkFailure : AuthenticationError
    object Unknown : AuthenticationError
}