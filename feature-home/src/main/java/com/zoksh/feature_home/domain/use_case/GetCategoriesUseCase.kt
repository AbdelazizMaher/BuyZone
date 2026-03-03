package com.zoksh.feature_home.domain.use_case

import com.zoksh.core_common.domain.error.DataError
import com.zoksh.core_common.domain.result.Result
import com.zoksh.feature_home.domain.model.Category
import com.zoksh.feature_home.domain.repository.HomeRepository

class GetCategoriesUseCase(private val repo: HomeRepository) {
    suspend operator fun invoke(): Result<List<Category>, DataError.Network> = repo.getCategories()
}
