package com.zoksh.buyzone.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.zoksh.buyzone.navigation.handlers.splash.SplashNavHandler
import com.zoksh.feature_splash.presentation.navigation.SplashDestination
import com.zoksh.feature_splash.presentation.screen.SplashScreen
import com.zoksh.feature_splash.presentation.viewmodel.SplashViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.splashGraph(
    navController: NavHostController,
    onShowBottomBar: (Boolean) -> Unit
) {
    composable<SplashDestination.Splash> {
        onShowBottomBar(false)
        val viewModel: SplashViewModel = koinViewModel()
        SplashNavHandler(navController, viewModel)
        SplashScreen()
    }
}
