package com.zoksh.network_apollo.apollo.client

import com.apollographql.apollo.ApolloClient
import com.zoksh.network_apollo.apollo.interceptor.SessionExpiryInterceptor
import com.zoksh.network_apollo.BuildConfig

fun createApolloClient(
    expiryInterceptor: SessionExpiryInterceptor
): ApolloClient {
    return ApolloClient.Builder()
        .serverUrl(BuildConfig.SHOPIFY_STOREFRONT_URL)
        .addHttpHeader(
            "X-Shopify-Storefront-Access-Token",
            BuildConfig.SHOPIFY_STOREFRONT_TOKEN
        )
        .addHttpInterceptor(expiryInterceptor)
        .build()
}