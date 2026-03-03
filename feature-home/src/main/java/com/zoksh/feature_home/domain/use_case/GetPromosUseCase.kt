package com.zoksh.feature_home.domain.use_case

import com.zoksh.core_common.domain.error.DataError
import com.zoksh.core_common.domain.result.Result
import com.zoksh.feature_home.domain.model.Promo
import com.zoksh.feature_home.domain.repository.HomeRepository

class GetPromosUseCase(private val repo: HomeRepository) {
    suspend operator fun invoke(): Result<List<Promo>, DataError.Network> = repo.getPromos()
}
