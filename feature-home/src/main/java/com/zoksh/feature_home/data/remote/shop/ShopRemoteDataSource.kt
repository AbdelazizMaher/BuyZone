package com.zoksh.feature_home.data.remote.shop

import com.zoksh.core_common.domain.error.DataError
import com.zoksh.core_common.domain.result.Result
import com.zoksh.feature_home.domain.model.Brand
import com.zoksh.feature_home.domain.model.Category
import com.zoksh.feature_home.domain.model.Product


interface ShopRemoteDataSource {
    suspend fun getCategories(): Result<List<Category>, DataError.Network>
    suspend fun getBrands(): Result<List<Brand>, DataError.Network>
    suspend fun getTrendingProducts(): Result<List<Product>, DataError.Network>
}
