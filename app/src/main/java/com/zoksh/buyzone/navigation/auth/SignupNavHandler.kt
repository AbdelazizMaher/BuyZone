package com.zoksh.buyzone.navigation.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.zoksh.feature_authentication.presentation.signup.contract.SignupContract
import com.zoksh.feature_authentication.presentation.signup.viewmodel.SignupViewModel
import com.zoksh.feature_home.presentation.navigation.HomeDestination

@Composable
fun SignupNavHandler(
    navController: NavHostController,
    viewModel: SignupViewModel,
) {
    LaunchedEffect(viewModel) {
        viewModel.event.collect { effect ->
            when (effect) {
                SignupContract.Effect.NavigateBack -> {}
                SignupContract.Effect.NavigateToLogin -> {}
                is SignupContract.Effect.ShowError -> {}
                SignupContract.Effect.GuestAccess -> {}
                is SignupContract.Effect.SignupSuccess -> {
                    navController.navigate(HomeDestination.Home)
                }
            }
        }
    }
}