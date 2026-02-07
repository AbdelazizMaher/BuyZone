package com.zoksh.core_session.identity.store

import com.zoksh.core_session.identity.model.User
import com.zoksh.core_session.identity.store.secure_storage.UserStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class UserStoreImpl(
    private val userStorage: UserStorage
): UserStore {

    private val _user = MutableStateFlow<User?>(null)
    override val user: StateFlow<User?> = _user

    override suspend fun save(user: User) {
        _user.value = user
        userStorage.save(user)
    }

    override suspend fun clear() {
        _user.value = null
        userStorage.clear()
    }
}