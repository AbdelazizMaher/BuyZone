package com.zoksh.feature_authentication.domain.usecase

import com.zoksh.feature_authentication.domain.model.AuthenticationError
import com.zoksh.feature_authentication.domain.model.AuthenticationResult
import com.zoksh.core_session.identity.model.User
import com.zoksh.core_session.identity.model.UserState
import com.zoksh.feature_authentication.domain.repository.AuthenticationRepository

class UserSetupUseCase(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(
        authUser: User,
    ): AuthenticationResult {
        val user = when (val result = repository.getUser(authUser.id)) {
            is AuthenticationResult.Success -> result.user
            is AuthenticationResult.Failure -> {
                if (result.error == AuthenticationError.UserNotRegistered) {
                    when (val createdResult = repository.createUser(authUser)) {
                        is AuthenticationResult.Success -> createdResult.user.copy(state = UserState.PROFILE_CREATED)
                        is AuthenticationResult.Failure -> return createdResult
                        else -> return AuthenticationResult.Failure(AuthenticationError.Unknown)
                    }
                } else return result
            }

            else -> return AuthenticationResult.Failure(AuthenticationError.Unknown)
        }

        return when (repository.authenticateShop(user)) {
            is AuthenticationResult.Success -> {
                repository.updateUser(user.copy(state = UserState.SHOP_LINKED))
                AuthenticationResult.Success(user.copy(state = UserState.SHOP_LINKED))
            }

            else -> AuthenticationResult.GuestAccess
        }
    }
}