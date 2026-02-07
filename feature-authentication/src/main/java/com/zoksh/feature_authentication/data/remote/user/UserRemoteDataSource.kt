package com.zoksh.feature_authentication.data.remote.user

import com.zoksh.core_session.identity.model.User

interface UserRemoteDataSource {
    suspend fun getUser(userId: String): User?
    suspend fun saveUser(user: User)
}
