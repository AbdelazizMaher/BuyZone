package com.zoksh.buyzone.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.facebook.CallbackManager
import com.zoksh.buyzone.navigation.handlers.auth.LoginNavHandler
import com.zoksh.buyzone.navigation.handlers.auth.SignupNavHandler
import com.zoksh.feature_authentication.presentation.login.screen.LoginScreen
import com.zoksh.feature_authentication.presentation.login.viewmodel.LoginViewModel
import com.zoksh.feature_authentication.presentation.navigation.AuthDestination
import com.zoksh.feature_authentication.presentation.signup.screen.SignupScreen
import com.zoksh.feature_authentication.presentation.signup.viewmodel.SignupViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState,
    callbackManager: CallbackManager,
    innerPadding: PaddingValues,
    onShowBottomBar: (Boolean) -> Unit
) {
    navigation<AuthDestination>(
        startDestination = AuthDestination.Login
    ) {
        composable<AuthDestination.Login> {
            onShowBottomBar(false)
            val viewModel: LoginViewModel = koinViewModel()
            LoginNavHandler(navController, viewModel, snackBarHostState, callbackManager)
            LoginScreen(viewModel = viewModel, innerPadding = innerPadding)
        }

        composable<AuthDestination.SignUp> {
            onShowBottomBar(false)
            val viewModel: SignupViewModel = koinViewModel()
            SignupNavHandler(navController, viewModel, snackBarHostState)
            SignupScreen(viewModel = viewModel, innerPadding = innerPadding)
        }
    }
}
