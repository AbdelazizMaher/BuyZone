package com.zoksh.buyzone.navigation.handlers.auth

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.zoksh.core_ui.snackbar.component.AppSnackBarVisuals
import com.zoksh.feature_authentication.presentation.navigation.AuthDestination
import com.zoksh.feature_authentication.presentation.signup.contract.SignupContract
import com.zoksh.feature_authentication.presentation.signup.viewmodel.SignupViewModel
import com.zoksh.feature_home.presentation.navigation.HomeDestination

@Composable
fun SignupNavHandler(
    navController: NavHostController,
    viewModel: SignupViewModel,
    snackBarHostState: SnackbarHostState,
) {
    LaunchedEffect(viewModel) {
        viewModel.event.collect { effect ->
            when (effect) {
                SignupContract.Effect.NavigateBack -> {
                    navController.popBackStack()
                }

                SignupContract.Effect.NavigateToLogin -> {
                    navController.navigate(AuthDestination.Login)
                }

                is SignupContract.Effect.ShowError -> {
                    snackBarHostState.showSnackbar(
                        visuals = AppSnackBarVisuals(effect.message)
                    )
                }

                SignupContract.Effect.GuestAccess -> {
                    navController.navigate(HomeDestination.Home)
                }

                is SignupContract.Effect.SignupSuccess -> {
                    navController.navigate(HomeDestination.Home)
                }
            }
        }
    }
}