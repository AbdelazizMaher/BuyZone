package com.zoksh.buyzone.navigation.auth

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.navigation.NavHostController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.zoksh.buyzone.R
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract.Intent.*
import com.zoksh.feature_authentication.presentation.login.viewmodel.LoginViewModel
import com.zoksh.feature_authentication.presentation.navigation.AuthDestination

@Composable
fun LoginNavHandler(
    navController: NavHostController,
    viewModel: LoginViewModel,
) {
    val activity = LocalContext.current as? Activity ?: return

    LaunchedEffect(viewModel) {
        viewModel.event.collect { effect ->
            when (effect) {
                LoginContract.Effect.NavigateToForgotPassword -> TODO()
                LoginContract.Effect.NavigateToSignup -> {
                    navController.navigate(AuthDestination.SignUp)
                }

                is LoginContract.Effect.ShowError -> TODO()
                LoginContract.Effect.GuestAccess -> TODO()
                LoginContract.Effect.StartFacebookAuth -> TODO()
                LoginContract.Effect.StartGoogleAuth -> handleGoogleAuth(
                    activity = activity,
                    onSuccess = { token ->
                        viewModel.handleIntent(GoogleAuthSuccess(token))
                    },
                    onFailure = { error ->
                        viewModel.handleIntent(GoogleAuthFailure(error))
                    }
                )

                is LoginContract.Effect.LoginSuccess -> TODO()
            }
        }
    }
}

private suspend fun handleGoogleAuth(
    activity: Activity,
    onSuccess: (token: String) -> Unit,
    onFailure: (error: String) -> Unit
) {
    val credentialManager = CredentialManager.create(activity)
    val googleIdOption = GetGoogleIdOption.Builder()
        .setServerClientId(activity.getString(R.string.default_web_client_id))
        .setFilterByAuthorizedAccounts(true)
        .build()

    val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

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