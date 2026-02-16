package com.zoksh.buyzone.navigation.handlers.auth

import androidx.activity.compose.LocalActivity
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.facebook.CallbackManager
import com.zoksh.core_common.presentation.mvi.ObserveAsEvents
import com.zoksh.core_ui.snackbar.component.AppSnackBarVisuals
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract
import com.zoksh.feature_authentication.presentation.login.viewmodel.LoginViewModel
import com.zoksh.feature_authentication.presentation.navigation.AuthDestination
import com.zoksh.feature_home.presentation.navigation.HomeDestination
import kotlinx.coroutines.launch


@Composable
fun LoginNavHandler(
    navController: NavHostController,
    viewModel: LoginViewModel,
    snackBarHostState: SnackbarHostState,
    callbackManager: CallbackManager
) {
    val activity = LocalActivity.current ?: return
    val scope = rememberCoroutineScope()

    ObserveAsEvents(viewModel.event) { effect ->
        when (effect) {
            LoginContract.Effect.NavigateToForgotPassword -> {
            }

            LoginContract.Effect.NavigateToSignup -> {
                navController.navigate(AuthDestination.SignUp)
            }

            is LoginContract.Effect.ShowError -> {
                scope.launch {
                    snackBarHostState.showSnackbar(
                        visuals = AppSnackBarVisuals(effect.message)
                    )
                }
            }

            LoginContract.Effect.GuestAccess -> {
                navController.navigate(HomeDestination.Home) {
                    popUpTo(0) { inclusive = true }
                }
            }

            LoginContract.Effect.StartFacebookAuth -> {
                SocialAuthManager.handleFacebookAuth(
                    activity = activity,
                    callbackManager = callbackManager,
                    onSuccess = { token ->
                        viewModel.handleIntent(LoginContract.Intent.FacebookAuthSuccess(token))
                    },
                    onFailure = { error ->
                        viewModel.handleIntent(LoginContract.Intent.FacebookAuthFailure(error))
                    }
                )
            }

            LoginContract.Effect.StartGoogleAuth -> {
                scope.launch {
                    SocialAuthManager.handleGoogleAuth(
                        activity = activity,
                        onSuccess = { token ->
                            viewModel.handleIntent(LoginContract.Intent.GoogleAuthSuccess(token))
                        },
                        onFailure = { error ->
                            viewModel.handleIntent(LoginContract.Intent.GoogleAuthFailure(error))
                        }
                    )
                }
            }

            is LoginContract.Effect.LoginSuccess -> {
                navController.navigate(HomeDestination.Home) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }
    }
}
