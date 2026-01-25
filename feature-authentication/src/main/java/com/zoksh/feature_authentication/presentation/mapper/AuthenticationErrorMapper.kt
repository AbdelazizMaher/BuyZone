package com.zoksh.feature_authentication.presentation.mapper

import com.zoksh.core_ui.snackbar.model.AppMessage
import com.zoksh.core_ui.snackbar.model.AppMessageAction
import com.zoksh.core_ui.snackbar.model.MessageType
import com.zoksh.feature_authentication.domain.model.AuthenticationError

fun AuthenticationError.toAppMessage(): AppMessage {
    return when (this) {
        AuthenticationError.InvalidCredentials ->
            AppMessage(
                text = "Invalid credentials",
                type = MessageType.ERROR
            )

        AuthenticationError.UserAlreadyExists ->
            AppMessage(
                text = "User already exists",
                type = MessageType.WARNING
            )

        AuthenticationError.UserNotRegistered ->
            AppMessage(
                text = "User not registered",
                type = MessageType.ERROR,
                action = AppMessageAction(
                    label = "Sign up",
                    onClick = {  }
                )
            )

        AuthenticationError.ShopLinkFailed ->
            AppMessage(
                text = "Could not link shop",
                type = MessageType.ERROR
            )

        AuthenticationError.NetworkFailure ->
            AppMessage(
                text = "Network error. Check connection.",
                type = MessageType.WARNING
            )

        AuthenticationError.Unknown ->
            AppMessage(
                text = "Something went wrong",
                type = MessageType.ERROR
            )
    }
}
