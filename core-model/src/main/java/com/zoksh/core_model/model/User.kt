package com.zoksh.core_model.model

data class User(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val isShopCreated: Boolean = false,
    val cartId: String = "",
    val state: UserState = UserState.UNAUTHENTICATED
)