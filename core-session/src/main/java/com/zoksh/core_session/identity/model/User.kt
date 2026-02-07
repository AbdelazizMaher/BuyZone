package com.zoksh.core_session.identity.model

data class User(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val image: String = "",
    val isShopCreated: Boolean = false,
    val cartId: String = "",
    val state: UserState = UserState.UNAUTHENTICATED
)