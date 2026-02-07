package com.zoksh.core_session.identity.store

import com.zoksh.core_session.identity.model.User
import kotlinx.coroutines.flow.StateFlow

interface UserStore {
    val user: StateFlow<User?>
    fun save(user: User)
    fun clear()
}