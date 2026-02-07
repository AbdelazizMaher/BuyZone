package com.zoksh.feature_home.domain.use_case

import com.zoksh.feature_home.domain.repository.HomeRepository

class GetPromosUseCase(private val repo: HomeRepository) {
    suspend operator fun invoke() = repo.getPromos()
}