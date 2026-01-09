package com.zoksh.feature_authentication.data.repository

import com.apollographql.apollo.ApolloClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.zoksh.feature_authentication.domain.model.AuthenticationCredential
import com.zoksh.feature_authentication.domain.model.AuthenticationResult
import com.zoksh.feature_authentication.domain.model.User
import com.zoksh.feature_authentication.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val apolloClient: ApolloClient

): AuthenticationRepository {
    override fun authenticate(credential: AuthenticationCredential): AuthenticationResult {
        TODO("Not yet implemented")
    }

    override fun getUser(userId: String): AuthenticationResult {
        TODO("Not yet implemented")
    }

    override fun createUser(user: User): AuthenticationResult {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: User): AuthenticationResult {
        TODO("Not yet implemented")
    }

    override fun authenticateShop(
        email: String,
        shopId: String
    ): AuthenticationResult {
        TODO("Not yet implemented")
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }
}