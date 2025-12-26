package com.zoksh.buyzone.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zoksh.feature_authentication.presentation.login.screen.LoginScreen
import com.zoksh.feature_authentication.presentation.navigation.AuthDestination
import com.zoksh.feature_authentication.presentation.signup.screen.SignupScreen
import com.zoksh.feature_onboarding.presentation.OnBoardingDestination
import com.zoksh.feature_onboarding.presentation.screen.OnBoardingScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = OnBoardingDestination.onBoarding,
    ) {
        composable<OnBoardingDestination.onBoarding> {
            OnBoardingScreen()
        }
        composable<AuthDestination.Login> {
            LoginScreen()
        }
        composable<AuthDestination.SignUp> {
            SignupScreen()
        }
    }
}