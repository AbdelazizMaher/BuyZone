package com.zoksh.feature_authentication.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.yourapp.auth.shopify.CustomerAccessTokenCreateMutation
import com.yourapp.auth.shopify.CustomerCreateMutation
import com.yourapp.auth.shopify.type.CustomerAccessTokenCreateInput
import com.yourapp.auth.shopify.type.CustomerCreateInput
import com.zoksh.core_model.model.User
import com.zoksh.core_model.model.UserState
import com.zoksh.core_session.session.model.Session
import com.zoksh.core_session.session.store.SessionStore
import com.zoksh.feature_authentication.data.mapper.authCall
import com.zoksh.feature_authentication.data.mapper.toUser
import com.zoksh.feature_authentication.domain.model.AuthenticationCredential
import com.zoksh.feature_authentication.domain.model.AuthenticationError
import com.zoksh.feature_authentication.domain.model.AuthenticationProvider
import com.zoksh.feature_authentication.domain.model.AuthenticationResult
import com.zoksh.feature_authentication.domain.repository.AuthenticationRepository
import kotlinx.coroutines.tasks.await
import java.io.IOException
import java.time.Instant

class AuthenticationRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val apolloClient: ApolloClient,
    private val sessionStore: SessionStore

) : AuthenticationRepository {
    override suspend fun authenticate(credential: AuthenticationCredential): AuthenticationResult {
        return when (credential) {
            is AuthenticationCredential.EmailAndPassword -> {
                when (credential.provider) {
                    AuthenticationProvider.EMAIL_LOGIN -> loginWithEmailAndPassword(credential)
                    AuthenticationProvider.EMAIL_SIGNUP -> signupWithEmailAndPassword(credential)
                    else -> AuthenticationResult.Failure(AuthenticationError.Unknown)
                }
            }

            is AuthenticationCredential.Social -> {
                when (credential.provider) {
                    AuthenticationProvider.GOOGLE -> loginWithGoogle(credential)
                    AuthenticationProvider.FACEBOOK -> loginWithFacebook(credential)
                    else -> AuthenticationResult.Failure(AuthenticationError.Unknown)
                }
            }
        }
    }

    override suspend fun getUser(userId: String): AuthenticationResult {
        return try {
            val snapshot = firebaseFirestore
                .collection("users")
                .document(userId)
                .get()
                .await()

            val user = snapshot.toObject(User::class.java)
            if (user != null) {
                AuthenticationResult.Success(user)
            } else {
                AuthenticationResult.Failure(AuthenticationError.UserNotRegistered)
            }

        } catch (_: IOException) {
            AuthenticationResult.Failure(AuthenticationError.NetworkFailure)
        } catch (_: Exception) {
            AuthenticationResult.Failure(AuthenticationError.Unknown)
        }
    }

    override suspend fun createUser(user: User): AuthenticationResult {
        return try {
            firebaseFirestore
                .collection("users")
                .document(user.id)
                .set(user)
                .await()

            AuthenticationResult.Success(user)

        } catch (_: IOException) {
            AuthenticationResult.Failure(AuthenticationError.NetworkFailure)
        } catch (_: Exception) {
            AuthenticationResult.Failure(AuthenticationError.Unknown)
        }
    }

    override suspend fun updateUser(user: User): AuthenticationResult {
        return try {
            firebaseFirestore
                .collection("users")
                .document(user.id)
                .set(user)
                .await()

            AuthenticationResult.Success(user)

        } catch (_: IOException) {
            AuthenticationResult.Failure(AuthenticationError.NetworkFailure)
        } catch (_: Exception) {
            AuthenticationResult.Failure(AuthenticationError.Unknown)
        }
    }

    override suspend fun authenticateShop(
        user: User
    ): AuthenticationResult {
        return try {
            if (!user.isShopCreated) {
                return when (val result = createCustomer(user = user)) {
                    is AuthenticationResult.Failure -> result
                    else -> customerAccessTokenCreate(user = user)
                }
            }

            return customerAccessTokenCreate(user = user)

        } catch (_: IOException) {
            AuthenticationResult.Failure(AuthenticationError.NetworkFailure)
        } catch (_: Exception) {
            AuthenticationResult.Failure(AuthenticationError.Unknown)
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()
        sessionStore.clear(expired = false)
    }

    private suspend fun loginWithEmailAndPassword(credential: AuthenticationCredential.EmailAndPassword): AuthenticationResult =
        authCall {
            val result = firebaseAuth
                .signInWithEmailAndPassword(credential.email, credential.password)
                .await()

            result.user!!.toUser()
        }

    private suspend fun signupWithEmailAndPassword(credential: AuthenticationCredential.EmailAndPassword): AuthenticationResult =
        authCall {
            val result = firebaseAuth
                .createUserWithEmailAndPassword(credential.email, credential.password)
                .await()

            result.user!!.toUser()
        }

    private suspend fun loginWithGoogle(credential: AuthenticationCredential.Social): AuthenticationResult =
        authCall {
            val firebaseCredential =
                GoogleAuthProvider.getCredential(credential.token, null)

            val result = firebaseAuth
                .signInWithCredential(firebaseCredential)
                .await()

            result.user!!.toUser()
        }

    private suspend fun loginWithFacebook(credential: AuthenticationCredential.Social): AuthenticationResult =
        authCall {
            val firebaseCredential = 
                FacebookAuthProvider.getCredential(credential.token)

            val result = firebaseAuth
                .signInWithCredential(firebaseCredential)
                .await()

            result.user!!.toUser()
        }

    private suspend fun createCustomer(user: User): AuthenticationResult {
        val response = apolloClient
            .mutation(
                CustomerCreateMutation(
                    input = CustomerCreateInput(
                        email = user.email,
                        password = user.id,
                        firstName = Optional.Present(user.name),
                    )
                )
            )
            .execute()

        return if (response.hasErrors()) {
            AuthenticationResult.Failure(AuthenticationError.ShopLinkFailed)
        } else {
            AuthenticationResult.Success(
                user = user.copy(
                    state = UserState.SHOP_LINKED,
                    isShopCreated = true
                )
            )
        }

    }

    private suspend fun customerAccessTokenCreate(user: User): AuthenticationResult {
        val response = apolloClient
            .mutation(
                CustomerAccessTokenCreateMutation(
                    input = CustomerAccessTokenCreateInput(
                        email = user.email,
                        password = user.id,
                    )
                )
            )
            .execute()

        return if (response.hasErrors()) {
            AuthenticationResult.Failure(AuthenticationError.ShopLinkFailed)
        } else {
            val accessToken = response.data?.customerAccessTokenCreate?.customerAccessToken?.accessToken
            val expiresAt = response.data?.customerAccessTokenCreate?.customerAccessToken?.expiresAt

            val expirationTimestamp = expiresAt?.let { Instant.parse(it.toString()).toEpochMilli() }

            sessionStore.update(
                Session(
                    accessToken = accessToken,
                    expiresIn = expirationTimestamp,
                    isGuest = false
                )
            )
            AuthenticationResult.Success(
                user = user.copy(
                    state = UserState.SHOP_LINKED,
                    isShopCreated = true
                )
            )
        }
    }
}
