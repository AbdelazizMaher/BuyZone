package com.zoksh.feature_authentication.data.repository

import com.zoksh.core_session.identity.model.User
import com.zoksh.core_session.identity.model.UserState
import com.zoksh.core_session.identity.store.UserStore
import com.zoksh.core_session.session.model.Session
import com.zoksh.core_session.session.store.SessionStore
import com.zoksh.feature_authentication.data.mapper.authCall
import com.zoksh.feature_authentication.data.remote.auth.AuthRemoteDataSource
import com.zoksh.feature_authentication.data.remote.shop.ShopRemoteDataSource
import com.zoksh.feature_authentication.data.remote.user.UserRemoteDataSource
import com.zoksh.feature_authentication.domain.model.AuthenticationCredential
import com.zoksh.feature_authentication.domain.model.AuthenticationError
import com.zoksh.feature_authentication.domain.model.AuthenticationProvider
import com.zoksh.feature_authentication.domain.model.AuthenticationResult
import com.zoksh.feature_authentication.domain.repository.AuthenticationRepository
import java.io.IOException

class AuthenticationRepositoryImpl(
    private val authDataSource: AuthRemoteDataSource,
    private val userDataSource: UserRemoteDataSource,
    private val shopDataSource: ShopRemoteDataSource,
    private val sessionStore: SessionStore,
    private val userStore: UserStore
) : AuthenticationRepository {

    override suspend fun authenticate(credential: AuthenticationCredential): AuthenticationResult {
        return authCall {
            when (credential) {
                is AuthenticationCredential.EmailAndPassword -> {
                    if (credential.provider == AuthenticationProvider.EMAIL_LOGIN) {
                        authDataSource.signInWithEmail(credential)
                    } else {
                        authDataSource.signUpWithEmail(credential)
                    }
                }
                is AuthenticationCredential.Social -> {
                    if (credential.provider == AuthenticationProvider.GOOGLE) {
                        authDataSource.signInWithGoogle(credential)
                    } else {
                        authDataSource.signInWithFacebook(credential)
                    }
                }
            }
        }
    }

    override suspend fun getUser(userId: String): AuthenticationResult = try {
        userDataSource.getUser(userId)?.let {
            AuthenticationResult.Success(it)
        } ?: AuthenticationResult.Failure(AuthenticationError.UserNotRegistered)
    } catch (e: Exception) {
        mapError(e)
    }

    override suspend fun createUser(user: User): AuthenticationResult = try {
        userDataSource.saveUser(user)
        AuthenticationResult.Success(user)
    } catch (e: Exception) {
        mapError(e)
    }

    override suspend fun updateUser(user: User): AuthenticationResult = try {
        userDataSource.saveUser(user)
        AuthenticationResult.Success(user)
    } catch (e: Exception) {
        mapError(e)
    }

    override suspend fun authenticateShop(user: User): AuthenticationResult {
        return try {
            if (!user.isShopCreated) {
                val created = shopDataSource.createCustomer(user)
                if (!created) return AuthenticationResult.Failure(AuthenticationError.ShopLinkFailed)
            }

            val shopToken = shopDataSource.createAccessToken(user)
                ?: return AuthenticationResult.Failure(AuthenticationError.ShopLinkFailed)

            sessionStore.update(
                Session(
                    accessToken = shopToken.token,
                    expiresIn = shopToken.expiresAt,
                    isGuest = false
                )
            )

            val updatedUser = user.copy(state = UserState.SHOP_LINKED, isShopCreated = true)
            userStore.save(updatedUser)
            AuthenticationResult.Success(updatedUser)

        } catch (e: Exception) {
            mapError(e)
        }
    }

    override suspend fun signOut() {
        authDataSource.signOut()
        sessionStore.clear(expired = false)
        userStore.clear()
    }

    private fun mapError(e: Exception): AuthenticationResult = when (e) {
        is IOException -> AuthenticationResult.Failure(AuthenticationError.NetworkFailure)
        else -> AuthenticationResult.Failure(AuthenticationError.Unknown)
    }
}
