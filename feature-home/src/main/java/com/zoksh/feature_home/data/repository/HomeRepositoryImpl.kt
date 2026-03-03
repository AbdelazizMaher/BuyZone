package com.zoksh.feature_home.data.repository

import com.zoksh.core_common.domain.connectivity.ConnectivityObserver
import com.zoksh.core_common.domain.error.DataError
import com.zoksh.core_common.domain.result.Result
import com.zoksh.core_session.identity.model.AppAuthState
import com.zoksh.core_session.identity.provider.AuthStateProvider
import com.zoksh.feature_home.data.remote.shop.ShopRemoteDataSource
import com.zoksh.feature_home.domain.model.Brand
import com.zoksh.feature_home.domain.model.Category
import com.zoksh.feature_home.domain.model.Header
import com.zoksh.feature_home.domain.model.Product
import com.zoksh.feature_home.domain.model.Promo
import com.zoksh.feature_home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class HomeRepositoryImpl(
    private val shopRemoteDataSource: ShopRemoteDataSource,
    private val authStateProvider: AuthStateProvider,
    private val connectivityObserver: ConnectivityObserver
) : HomeRepository {

    override suspend fun getHeader(): Flow<Header> {
        return authStateProvider.authState.map { authState ->
            when (authState) {
                is AppAuthState.Authenticated -> Header(
                    userName = authState.user.name,
                    userImage = authState.user.image,
                    notificationCount = 0
                )
                AppAuthState.Guest -> Header(
                    userName = "Guest",
                    userImage = null,
                    notificationCount = 0
                )
            }
        }
    }

    override suspend fun getPromos(): Result<List<Promo>, DataError.Network> {
        TODO()
    }

    override suspend fun getCategories(): Result<List<Category>, DataError.Network> {
        return executeRemoteCall { shopRemoteDataSource.getCategories() }
    }

    override suspend fun getPopularBrands(): Result<List<Brand>, DataError.Network> {
        return executeRemoteCall { shopRemoteDataSource.getBrands() }
    }

    override suspend fun getTrendingProducts(): Result<List<Product>, DataError.Network> {
        return executeRemoteCall { shopRemoteDataSource.getTrendingProducts() }
    }

    private suspend fun <T> executeRemoteCall(
        call: suspend () -> Result<T, DataError.Network>
    ): Result<T, DataError.Network> {
        if (!connectivityObserver.isConnected.first()) {
            return Result.Error(DataError.Network.NO_INTERNET)
        }
        return call()
    }
}
