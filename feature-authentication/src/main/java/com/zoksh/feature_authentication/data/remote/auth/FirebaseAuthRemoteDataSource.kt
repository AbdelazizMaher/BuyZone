package com.zoksh.feature_authentication.data.remote.auth

import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.zoksh.core_session.identity.model.User
import com.zoksh.feature_authentication.data.mapper.toUser
import com.zoksh.feature_authentication.domain.model.AuthenticationCredential
import kotlinx.coroutines.tasks.await

class FirebaseAuthRemoteDataSource(
    private val firebaseAuth: FirebaseAuth
) : AuthRemoteDataSource {
    override suspend fun signInWithEmail(credential: AuthenticationCredential.EmailAndPassword): User {
        val result = firebaseAuth
            .signInWithEmailAndPassword(credential.email, credential.password)
            .await()
        return result.user!!.toUser()
    }

    override suspend fun signUpWithEmail(credential: AuthenticationCredential.EmailAndPassword): User {
        val result = firebaseAuth
            .createUserWithEmailAndPassword(credential.email, credential.password)
            .await()
        return result.user!!.toUser()
    }

    override suspend fun signInWithGoogle(credential: AuthenticationCredential.Social): User {
        val firebaseCredential = GoogleAuthProvider.getCredential(credential.token, null)
        val result = firebaseAuth.signInWithCredential(firebaseCredential).await()
        return result.user!!.toUser()
    }

    override suspend fun signInWithFacebook(credential: AuthenticationCredential.Social): User {
        val firebaseCredential = FacebookAuthProvider.getCredential(credential.token)
        val result = firebaseAuth.signInWithCredential(firebaseCredential).await()
        return result.user!!.toUser()
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}
