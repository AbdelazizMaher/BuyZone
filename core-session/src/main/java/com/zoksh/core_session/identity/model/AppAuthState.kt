package com.zoksh.core_session.identity.model

sealed interface AppAuthState {
    data object Guest : AppAuthState
    data class Authenticated(val user: User) : AppAuthState
}