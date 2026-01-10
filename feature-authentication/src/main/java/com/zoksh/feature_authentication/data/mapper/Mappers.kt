package com.zoksh.feature_authentication.data.mapper

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.zoksh.feature_authentication.domain.model.AuthenticationError
import com.zoksh.feature_authentication.domain.model.AuthenticationResult
import com.zoksh.feature_authentication.domain.model.User
import java.io.IOException

inline fun authCall(
    apiCall: () -> User,
): AuthenticationResult {
    return try {
        AuthenticationResult.Success(apiCall())
    } catch (_: FirebaseAuthInvalidCredentialsException) {
        AuthenticationResult.Failure(AuthenticationError.InvalidCredentials)
    } catch (_: FirebaseAuthUserCollisionException) {
        AuthenticationResult.Failure(AuthenticationError.UserAlreadyExists)
    } catch (_: IOException) {
        AuthenticationResult.Failure(AuthenticationError.NetworkFailure)
    } catch (_: Exception) {
        AuthenticationResult.Failure(AuthenticationError.Unknown)
    }
}

fun FirebaseUser.toUser(): User {
    return User(
        id = uid,
        email = email.orEmpty(),
    )
}