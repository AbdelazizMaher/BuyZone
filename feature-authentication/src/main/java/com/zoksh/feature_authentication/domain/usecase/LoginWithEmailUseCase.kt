package com.zoksh.feature_authentication.domain.usecase

import com.zoksh.feature_authentication.domain.repository.AuthenticationRepository

class LoginWithEmailUseCase(
    private val repository: AuthenticationRepository
) {
    operator fun invoke() {

    }
}