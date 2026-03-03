package com.zoksh.feature_home.domain.repository

import com.zoksh.core_common.domain.result.Result
import com.zoksh.core_common.domain.error.DataError
import com.zoksh.feature_home.domain.model.Brand
import com.zoksh.feature_home.domain.model.Category
import com.zoksh.feature_home.domain.model.Header
import com.zoksh.feature_home.domain.model.Product
import com.zoksh.feature_home.domain.model.Promo
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getHeader(): Flow<Header>

    suspend fun getPromos(): Result<List<Promo>, DataError.Network>

    suspend fun getCategories(): Result<List<Category>, DataError.Network>

    suspend fun getPopularBrands(): Result<List<Brand>, DataError.Network>

    suspend fun getTrendingProducts(): Result<List<Product>, DataError.Network>
}
