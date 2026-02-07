package com.zoksh.feature_home.domain.use_case

import com.zoksh.feature_home.domain.repository.HomeRepository

class GetTrendingProductsUseCase(private val repo: HomeRepository) {
    suspend operator fun invoke() = repo.getTrendingProducts()
}