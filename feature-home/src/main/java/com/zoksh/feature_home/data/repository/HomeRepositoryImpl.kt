package com.zoksh.feature_home.data.repository

import com.zoksh.core_common.domain.error.DataError
import com.zoksh.core_common.domain.result.Result
import com.zoksh.feature_home.data.remote.shop.ShopRemoteDataSource
import com.zoksh.feature_home.domain.model.Brand
import com.zoksh.feature_home.domain.model.Category
import com.zoksh.feature_home.domain.model.Header
import com.zoksh.feature_home.domain.model.Product
import com.zoksh.feature_home.domain.model.Promo
import com.zoksh.feature_home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val shopRemoteDataSource: ShopRemoteDataSource
) : HomeRepository {
    override suspend fun getHeader(): Result<Header, DataError.Network> {
        TODO("Not yet implemented")
    }

    override suspend fun getPromos(): Result<List<Promo>, DataError.Network> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategories(): Result<List<Category>, DataError.Network> {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularBrands(): Result<List<Brand>, DataError.Network> {
        TODO("Not yet implemented")
    }

    override suspend fun getTrendingProducts(): Result<List<Product>, DataError.Network> {
        TODO("Not yet implemented")
    }
}