package com.zoksh.feature_authentication.domain.model

data class User(
    val id: String,
    val email: String,
    val name: String? = null,
    val state: UserState = UserState.UNAUTHENTICATED
)