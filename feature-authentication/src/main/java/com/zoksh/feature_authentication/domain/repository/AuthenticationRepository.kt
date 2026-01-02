package com.zoksh.feature_authentication.domain.repository

import com.zoksh.feature_authentication.domain.model.AuthenticationCredential
import com.zoksh.feature_authentication.domain.model.AuthenticationResult
import com.zoksh.feature_authentication.domain.model.User

interface AuthenticationRepository {
    fun authenticate(credential: AuthenticationCredential): AuthenticationResult

    fun getUser(userId: String): AuthenticationResult
    fun createUser(user: User): AuthenticationResult
    fun updateUser(user: User): AuthenticationResult

    fun authenticateShop(
        email: String,
        shopId: String
    ): AuthenticationResult

    fun signOut()
}
