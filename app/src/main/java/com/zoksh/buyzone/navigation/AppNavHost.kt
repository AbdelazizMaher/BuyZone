package com.zoksh.buyzone.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zoksh.buyzone.navigation.auth.LoginNavHandler
import com.zoksh.buyzone.navigation.auth.SignupNavHandler
import com.zoksh.buyzone.navigation.onboarding.OnBoardingNavHandler
import com.zoksh.feature_authentication.presentation.login.screen.LoginScreen
import com.zoksh.feature_authentication.presentation.login.viewmodel.LoginViewModel
import com.zoksh.feature_authentication.presentation.navigation.AuthDestination
import com.zoksh.feature_authentication.presentation.signup.screen.SignupScreen
import com.zoksh.feature_authentication.presentation.signup.viewmodel.SignupViewModel
import com.zoksh.feature_onboarding.presentation.navigation.OnBoardingDestination
import com.zoksh.feature_onboarding.presentation.screen.OnBoardingScreen
import com.zoksh.feature_onboarding.presentation.viewmodel.OnBoardingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = OnBoardingDestination.OnBoarding,
    ) {
        composable<OnBoardingDestination.OnBoarding> {
            val viewModel: OnBoardingViewModel = koinViewModel()
            OnBoardingNavHandler(navController, viewModel)
            OnBoardingScreen(viewModel = viewModel)
        }
        composable<AuthDestination.Login> {
            val viewModel: LoginViewModel = koinViewModel()
            LoginNavHandler(navController, viewModel)
            LoginScreen()
        }
        composable<AuthDestination.SignUp> {
            val viewModel: SignupViewModel = koinViewModel()
            SignupNavHandler(navController, viewModel)
            SignupScreen()
        }
    }
}