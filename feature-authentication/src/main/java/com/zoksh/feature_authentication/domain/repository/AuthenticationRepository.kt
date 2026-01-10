package com.zoksh.feature_authentication.domain.repository

import com.zoksh.feature_authentication.domain.model.AuthenticationCredential
import com.zoksh.feature_authentication.domain.model.AuthenticationResult
import com.zoksh.feature_authentication.domain.model.User

interface AuthenticationRepository {
    suspend fun authenticate(credential: AuthenticationCredential): AuthenticationResult

    suspend fun getUser(userId: String): AuthenticationResult
    suspend fun createUser(user: User): AuthenticationResult
    suspend fun updateUser(user: User): AuthenticationResult

    suspend fun authenticateShop(
        user: User,
    ): AuthenticationResult

    fun signOut()
}
