package com.zoksh.buyzone.navigation.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.zoksh.feature_authentication.presentation.signup.contract.SignupContract
import com.zoksh.feature_authentication.presentation.signup.viewmodel.SignupViewModel

@Composable
fun SignupNavHandler(
    navController: NavHostController,
    viewModel: SignupViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.event.collect { effect ->
            when (effect) {
                SignupContract.Effect.NavigateBack -> {}
                SignupContract.Effect.NavigateToLogin -> {}
                is SignupContract.Effect.ShowError -> {}
                SignupContract.Effect.GuestAccess -> {}
                is SignupContract.Effect.SignupSuccess -> {}
            }
        }
    }
}