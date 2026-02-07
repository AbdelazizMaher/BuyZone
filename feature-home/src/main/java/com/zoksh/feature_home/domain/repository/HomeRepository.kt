package com.zoksh.feature_home.domain.repository

import com.zoksh.feature_home.domain.model.Brand
import com.zoksh.feature_home.domain.model.Category
import com.zoksh.feature_home.domain.model.Header
import com.zoksh.feature_home.domain.model.Product
import com.zoksh.feature_home.domain.model.Promo

interface HomeRepository {
    suspend fun getHeader(): Result<Header>

    suspend fun getPromos(): Result<List<Promo>>

    suspend fun getCategories(): Result<List<Category>>

    suspend fun getPopularBrands(): Result<List<Brand>>

    suspend fun getTrendingProducts(): Result<List<Product>>
}