package com.zoksh.buyzone.navigation.handlers.auth

import android.app.Activity
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.ClearCredentialException
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.zoksh.buyzone.R


object SocialAuthManager {

    suspend fun handleGoogleAuth(
        activity: Activity,
        onSuccess: (token: String) -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        val credentialManager = CredentialManager.create(activity)
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(activity.getString(R.string.default_web_client_id))
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try {
            credentialManager.clearCredentialState(ClearCredentialStateRequest())
        } catch (e: ClearCredentialException) {
            Log.e("SocialAuth", "Failed to clear credentials", e)
        }

        try {
            val result = credentialManager.getCredential(activity, request)
            if (result.credential is CustomCredential && result.credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val token = GoogleIdTokenCredential.createFrom(result.credential.data).idToken
                onSuccess(token)
            } else {
                onFailure("Invalid credential type")
            }
        } catch (e: Exception) {
            onFailure(e.message ?: "Unknown error")
        }
    }

    fun handleFacebookAuth(
        activity: Activity,
        callbackManager: CallbackManager,
        onSuccess: (token: String) -> Unit,
        onFailure: (error: String) -> Unit,
    ) {
        LoginManager.getInstance().logOut()
        LoginManager.getInstance().logInWithReadPermissions(activity, listOf("public_profile", "email"))
        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onCancel() {}

                override fun onError(error: FacebookException) {
                    onFailure(error.message ?: "Unknown error")
                }

                override fun onSuccess(result: LoginResult) {
                    onSuccess(result.accessToken.token)
                }
            })
    }
}
