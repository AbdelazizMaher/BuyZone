package com.zoksh.feature_home.data.remote.shop

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.exception.ApolloException
import com.zoksh.core_common.domain.error.DataError
import com.zoksh.core_common.domain.result.Result
import com.zoksh.core_common.domain.result.runResultCatching
import com.zoksh.feature_home.domain.model.Brand
import com.zoksh.feature_home.domain.model.Category
import com.zoksh.feature_home.domain.model.Product
import com.zoksh.home.shopify.BrandsQuery
import com.zoksh.home.shopify.CategoriesQuery
import com.zoksh.home.shopify.TrendingQuery
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class ApolloShopRemoteDataSource(
    private val apolloClient: ApolloClient,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ShopRemoteDataSource {

    override suspend fun getCategories(): Result<List<Category>, DataError.Network> {
        return safeApolloCall {
            val response = apolloClient.query(CategoriesQuery()).execute()
            response.data?.productTypes?.nodes?.map { node ->
                Category(id = node, name = node)
            } ?: emptyList()
        }
    }

    override suspend fun getBrands(): Result<List<Brand>, DataError.Network> {
        return safeApolloCall {
            val response = apolloClient.query(BrandsQuery(first = 10)).execute()
            response.data?.collections?.nodes?.map { node ->
                Brand(
                    id = node.id,
                    name = node.title,
                    logoUrl = node.image?.url?.toString() ?: ""
                )
            } ?: emptyList()
        }
    }

    override suspend fun getTrendingProducts(): Result<List<Product>, DataError.Network> {
        return safeApolloCall {
            val response = apolloClient.query(TrendingQuery()).execute()
            response.data?.products?.nodes?.map { node ->
                Product(
                    id = node.id,
                    name = node.title,
                    description = node.description,
                    price = node.variants.nodes.firstOrNull()?.price?.amount?.toString()?.toDouble() ?: 0.0,
                    currency = node.variants.nodes.firstOrNull()?.price?.currencyCode.toString(),
                    imageUrl = node.featuredImage?.url?.toString() ?: "",
                    brand = node.vendor,
                    isFavorite = false
                )
            } ?: emptyList()
        }
    }


    private suspend fun <T> safeApolloCall(call: suspend () -> T): Result<T, DataError.Network> {
        return withContext(ioDispatcher) {
            runResultCatching(
                errorMapper = { throwable ->
                    when (throwable) {
                        is IOException -> DataError.Network.NO_INTERNET
                        is ApolloException -> DataError.Network.SERVER_ERROR
                        else -> DataError.Network.UNKNOWN
                    }
                },
                block = { call() }
            )
        }
    }
}
