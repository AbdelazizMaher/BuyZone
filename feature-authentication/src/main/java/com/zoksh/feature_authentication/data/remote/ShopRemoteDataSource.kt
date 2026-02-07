package com.zoksh.feature_authentication.data.remote

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import com.yourapp.auth.shopify.CustomerAccessTokenCreateMutation
import com.yourapp.auth.shopify.CustomerCreateMutation
import com.yourapp.auth.shopify.type.CustomerAccessTokenCreateInput
import com.yourapp.auth.shopify.type.CustomerCreateInput
import com.zoksh.core_session.identity.model.User
import java.time.Instant

interface ShopRemoteDataSource {
    suspend fun createCustomer(user: User): Boolean
    suspend fun createAccessToken(user: User): ShopAccessToken?
}

data class ShopAccessToken(
    val token: String,
    val expiresAt: Long?
)

class ApolloShopRemoteDataSource(
    private val apolloClient: ApolloClient
) : ShopRemoteDataSource {
    override suspend fun createCustomer(user: User): Boolean {
        val response = apolloClient
            .mutation(
                CustomerCreateMutation(
                    input = CustomerCreateInput(
                        email = user.email,
                        password = user.id,
                        firstName = Optional.Present(user.name),
                    )
                )
            )
            .execute()
        return !response.hasErrors()
    }

    override suspend fun createAccessToken(user: User): ShopAccessToken? {
        val response = apolloClient
            .mutation(
                CustomerAccessTokenCreateMutation(
                    input = CustomerAccessTokenCreateInput(
                        email = user.email,
                        password = user.id,
                    )
                )
            )
            .execute()

        if (response.hasErrors()) return null

        val data = response.data?.customerAccessTokenCreate?.customerAccessToken
        val token = data?.accessToken ?: return null
        val expiresAt = data.expiresAt?.let { Instant.parse(it.toString()).toEpochMilli() }

        return ShopAccessToken(token, expiresAt)
    }
}
