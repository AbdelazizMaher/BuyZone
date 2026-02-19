package com.zoksh.feature_authentication.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data object AuthGraph

@Serializable
sealed interface AuthDestination {
    @Serializable object Login : AuthDestination

    @Serializable object SignUp : AuthDestination
}