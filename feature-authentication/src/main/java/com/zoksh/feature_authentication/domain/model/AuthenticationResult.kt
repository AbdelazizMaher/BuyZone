package com.zoksh.feature_authentication.domain.model

import com.zoksh.core_session.identity.model.User

sealed interface AuthenticationResult {
    data class Success(val user: User) : AuthenticationResult
    object GuestAccess : AuthenticationResult
    data class ValidationFailed(val errors: List<ValidationError>) : AuthenticationResult
    data class Failure(val error: AuthenticationError) : AuthenticationResult
}
