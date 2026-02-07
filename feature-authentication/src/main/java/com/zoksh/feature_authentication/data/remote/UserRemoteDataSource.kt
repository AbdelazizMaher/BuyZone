package com.zoksh.feature_authentication.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.zoksh.core_session.identity.model.User
import kotlinx.coroutines.tasks.await

interface UserRemoteDataSource {
    suspend fun getUser(userId: String): User?
    suspend fun saveUser(user: User)
}

class FirestoreUserRemoteDataSource(
    private val firestore: FirebaseFirestore
) : UserRemoteDataSource {
    override suspend fun getUser(userId: String): User? {
        val snapshot = firestore
            .collection("users")
            .document(userId)
            .get()
            .await()
        return snapshot.toObject(User::class.java)
    }

    override suspend fun saveUser(user: User) {
        firestore
            .collection("users")
            .document(user.id)
            .set(user)
            .await()
    }
}
