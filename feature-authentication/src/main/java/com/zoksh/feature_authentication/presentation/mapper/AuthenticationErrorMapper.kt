package com.zoksh.feature_authentication.presentation.mapper

import com.zoksh.feature_authentication.domain.model.AuthenticationError

fun AuthenticationError.toUiMessage(): String {
    return when (this) {
        AuthenticationError.InvalidCredentials -> "Invalid credentials"
        AuthenticationError.UserAlreadyExists -> "User already exists"
        AuthenticationError.UserNotRegistered -> "User not registered"
        AuthenticationError.ShopLinkFailed -> "Shop link failed"
        AuthenticationError.NetworkFailure -> "Network failure"
        AuthenticationError.Unknown -> "Unknown error"
    }
}