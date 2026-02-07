package com.zoksh.feature_authentication.data.remote.shop

import com.zoksh.core_session.identity.model.User

interface ShopRemoteDataSource {
    suspend fun createCustomer(user: User): Boolean
    suspend fun createAccessToken(user: User): ShopAccessToken?
}

data class ShopAccessToken(
    val token: String,
    val expiresAt: Long?
)
