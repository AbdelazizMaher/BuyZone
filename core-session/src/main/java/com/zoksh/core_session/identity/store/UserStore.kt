package com.zoksh.core_session.identity.store

import com.zoksh.core_session.identity.model.User
import kotlinx.coroutines.flow.StateFlow

interface UserStore {
    val user: StateFlow<User?>
    suspend fun save(user: User)
    suspend fun clear()
}