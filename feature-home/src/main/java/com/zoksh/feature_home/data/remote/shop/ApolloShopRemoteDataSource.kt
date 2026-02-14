package com.zoksh.feature_home.data.remote.shop

import com.apollographql.apollo.ApolloClient


class ApolloShopRemoteDataSource(
    private val apolloClient: ApolloClient
) : ShopRemoteDataSource {

}
