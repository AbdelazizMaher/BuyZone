package com.zoksh.feature_authentication.data.remote.auth

import com.zoksh.core_session.identity.model.User
import com.zoksh.feature_authentication.domain.model.AuthenticationCredential

interface AuthRemoteDataSource {
    suspend fun signInWithEmail(credential: AuthenticationCredential.EmailAndPassword): User
    suspend fun signUpWithEmail(credential: AuthenticationCredential.EmailAndPassword): User
    suspend fun signInWithGoogle(credential: AuthenticationCredential.Social): User
    suspend fun signInWithFacebook(credential: AuthenticationCredential.Social): User
    fun signOut()
}
