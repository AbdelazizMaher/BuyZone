package com.zoksh.feature_home.domain.use_case

import com.zoksh.core_common.domain.error.DataError
import com.zoksh.core_common.domain.result.Result
import com.zoksh.feature_home.domain.model.Product
import com.zoksh.feature_home.domain.repository.HomeRepository

class GetTrendingProductsUseCase(private val repo: HomeRepository) {
    suspend operator fun invoke(): Result<List<Product>, DataError.Network> = repo.getTrendingProducts()
}
