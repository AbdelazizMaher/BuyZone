package com.zoksh.feature_authentication.domain.usecase

import com.zoksh.feature_authentication.domain.model.AuthenticationCredential
import com.zoksh.feature_authentication.domain.model.AuthenticationError
import com.zoksh.feature_authentication.domain.model.AuthenticationResult
import com.zoksh.feature_authentication.domain.model.UserState
import com.zoksh.feature_authentication.domain.repository.AuthenticationRepository
import com.zoksh.feature_authentication.domain.validation.ValidationHandler

class LoginUseCase(
    private val userSetupUseCase: UserSetupUseCase,
    private val repository: AuthenticationRepository,
) {
    operator fun invoke(
        credential: AuthenticationCredential,
        validator: ValidationHandler
    ): AuthenticationResult {
        validator.handleAllErrors().also {
            if (it.isNotEmpty()) return AuthenticationResult.ValidationFailed(it)
        }

        return when (val result = repository.authenticate(credential)) {
            is AuthenticationResult.Success -> userSetupUseCase(result.user.copy(state = UserState.AUTHENTICATED))
            is AuthenticationResult.Failure -> AuthenticationResult.Failure(result.error)
            else -> AuthenticationResult.Failure(AuthenticationError.Unknown)
        }
    }
}