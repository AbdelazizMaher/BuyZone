package com.zoksh.buyzone.navigation.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.zoksh.feature_authentication.presentation.navigation.AuthDestination
import com.zoksh.feature_onboarding.presentation.contract.OnBoardingContract
import com.zoksh.feature_onboarding.presentation.viewmodel.OnBoardingViewModel

@Composable
fun OnBoardingNavHandler(
    navController: NavHostController,
    viewModel: OnBoardingViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.event.collect { effect ->
            when (effect) {
                OnBoardingContract.Effect.GetStarted -> navController.navigate(AuthDestination.Login)
            }
        }
    }
}