package com.zoksh.feature_home.domain.use_case

import com.zoksh.feature_home.domain.model.Header
import com.zoksh.feature_home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetHeaderUseCase(private val repo: HomeRepository) {
    suspend operator fun invoke(): Flow<Header> = repo.getHeader()
}
