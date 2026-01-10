package com.zoksh.core_session.session.model

data class Session(
    val accessToken: String?,
    val expiresIn: Long?,
    val isGuest: Boolean
)
