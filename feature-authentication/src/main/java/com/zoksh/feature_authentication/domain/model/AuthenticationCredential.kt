package com.zoksh.feature_authentication.domain.model

sealed interface AuthenticationCredential {
    data class EmailAndPassword(
        val email: String,
        val password: String,
        val provider: AuthenticationProvider
    ) : AuthenticationCredential

    data class Social(
        val token: String,
        val provider: AuthenticationProvider
    ) : AuthenticationCredential
}
