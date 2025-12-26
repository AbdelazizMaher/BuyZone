package com.zoksh.buyzone.navigation.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract
import com.zoksh.feature_authentication.presentation.login.viewmodel.LoginViewModel
import com.zoksh.feature_authentication.presentation.navigation.AuthDestination
import com.zoksh.feature_authentication.presentation.signup.screen.SignupScreen

@Composable
fun LoginNavHandler(
    navController: NavHostController,
    viewModel: LoginViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.event.collect { effect ->
            when (effect) {
                LoginContract.Effect.LoginSuccess -> TODO()
                LoginContract.Effect.NavigateToForgotPassword -> TODO()
                LoginContract.Effect.NavigateToSignup -> {
                    navController.navigate(AuthDestination.SignUp)
                }
                is LoginContract.Effect.ShowError -> TODO()
            }
        }
    }
}