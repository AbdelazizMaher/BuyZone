package com.zoksh.core_session.identity.store.secure_storage

import com.zoksh.core_session.identity.model.User

interface UserStorage {
    suspend fun save(user: User)
    suspend fun get(): User?
    suspend fun clear()
}
