package com.zoksh.feature_authentication.data.remote.shop

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import com.yourapp.auth.shopify.CustomerAccessTokenCreateMutation
import com.yourapp.auth.shopify.CustomerCreateMutation
import com.yourapp.auth.shopify.type.CustomerAccessTokenCreateInput
import com.yourapp.auth.shopify.type.CustomerCreateInput
import com.zoksh.core_session.identity.model.User
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

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
        
        val expiresAt = data.expiresAt?.let { dateString ->
            try {
                val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).apply {
                    timeZone = TimeZone.getTimeZone("UTC")
                }
                sdf.parse(dateString.toString())?.time
            } catch (_: Exception) {
                null
            }
        }

        return ShopAccessToken(token, expiresAt)
    }
}
