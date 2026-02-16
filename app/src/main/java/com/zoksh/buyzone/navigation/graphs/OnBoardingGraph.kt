package com.zoksh.buyzone.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.zoksh.buyzone.navigation.handlers.onboarding.OnBoardingNavHandler
import com.zoksh.feature_onboarding.presentation.navigation.OnBoardingDestination
import com.zoksh.feature_onboarding.presentation.screen.OnBoardingScreen
import com.zoksh.feature_onboarding.presentation.viewmodel.OnBoardingViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.onBoardingGraph(
    navController: NavHostController,
    onShowBottomBar: (Boolean) -> Unit
) {
    composable<OnBoardingDestination.OnBoarding> {
        onShowBottomBar(false)
        val viewModel: OnBoardingViewModel = koinViewModel()
        OnBoardingNavHandler(navController, viewModel)
        OnBoardingScreen(viewModel = viewModel)
    }
}
