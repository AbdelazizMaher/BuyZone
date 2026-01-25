package com.zoksh.buyzone.navigation.auth

import android.app.Activity
import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.ClearCredentialException
import androidx.navigation.NavHostController
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.zoksh.buyzone.R
import com.zoksh.core_ui.snackbar.component.AppSnackBarVisuals
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract.Intent.FacebookAuthFailure
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract.Intent.FacebookAuthSuccess
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract.Intent.GoogleAuthFailure
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract.Intent.GoogleAuthSuccess
import com.zoksh.feature_authentication.presentation.login.viewmodel.LoginViewModel
import com.zoksh.feature_authentication.presentation.navigation.AuthDestination
import com.zoksh.feature_home.presentation.navigation.HomeDestination

@Composable
fun LoginNavHandler(
    navController: NavHostController,
    viewModel: LoginViewModel,
    snackBarHostState: SnackbarHostState,
    callbackManager: CallbackManager
) {
    val activity = LocalContext.current as? Activity ?: return

    LaunchedEffect(viewModel) {
        viewModel.event.collect { effect ->
            when (effect) {
                LoginContract.Effect.NavigateToForgotPassword -> {

                }

                LoginContract.Effect.NavigateToSignup -> {
                    navController.navigate(AuthDestination.SignUp)
                }

                is LoginContract.Effect.ShowError -> {
                    snackBarHostState.showSnackbar(
                        visuals = AppSnackBarVisuals(effect.message)
                    )
                }

                LoginContract.Effect.GuestAccess -> {
                    navController.navigate(HomeDestination.Home)
                }

                LoginContract.Effect.StartFacebookAuth -> handleFacebookAuth(
                    activity = activity,
                    callbackManager = callbackManager,
                    onSuccess = { token ->
                        viewModel.handleIntent(FacebookAuthSuccess(token))
                    },
                    onFailure = { error ->
                        viewModel.handleIntent(FacebookAuthFailure(error))
                    }
                )

                LoginContract.Effect.StartGoogleAuth -> handleGoogleAuth(
                    activity = activity,
                    onSuccess = { token ->
                        viewModel.handleIntent(GoogleAuthSuccess(token))
                    },
                    onFailure = { error ->
                        viewModel.handleIntent(GoogleAuthFailure(error))
                    }
                )

                is LoginContract.Effect.LoginSuccess -> {
                    Log.e("LoginNavHandler", "LoginSuccess")
                    navController.navigate(HomeDestination.Home)
                }
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
        .setFilterByAuthorizedAccounts(false)
        .build()

    val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

    try {
        val clearRequest = ClearCredentialStateRequest()
        credentialManager.clearCredentialState(clearRequest)
    } catch (e: ClearCredentialException) {
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

private fun handleFacebookAuth(
    activity: Activity,
    callbackManager: CallbackManager,
    onSuccess: (token: String) -> Unit,
    onFailure: (error: String) -> Unit,
) {
    LoginManager.getInstance().logOut()
    LoginManager.getInstance().logInWithReadPermissions(activity, listOf("public_profile", "email"))
    LoginManager.getInstance()
        .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onCancel() {

            }

            override fun onError(error: FacebookException) {
                onFailure(error.message ?: "Unknown error")
            }

            override fun onSuccess(result: LoginResult) {
                val token1 = AccessToken.getCurrentAccessToken()
                Log.d("FB", "appId=${token1?.applicationId}")
                onSuccess(result.accessToken.token)
            }
        })
}