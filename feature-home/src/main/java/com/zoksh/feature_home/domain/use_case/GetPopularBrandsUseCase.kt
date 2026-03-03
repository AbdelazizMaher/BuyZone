package com.zoksh.feature_home.domain.use_case

import com.zoksh.core_common.domain.error.DataError
import com.zoksh.core_common.domain.result.Result
import com.zoksh.feature_home.domain.model.Brand
import com.zoksh.feature_home.domain.repository.HomeRepository

class GetPopularBrandsUseCase(private val repo: HomeRepository) {
    suspend operator fun invoke(): Result<List<Brand>, DataError.Network> = repo.getPopularBrands()
}
