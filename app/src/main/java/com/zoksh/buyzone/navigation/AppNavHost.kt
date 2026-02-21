package com.zoksh.buyzone.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.facebook.CallbackManager
import com.zoksh.buyzone.navigation.graphs.authGraph
import com.zoksh.buyzone.navigation.graphs.homeGraph
import com.zoksh.buyzone.navigation.graphs.onBoardingGraph
import com.zoksh.buyzone.navigation.graphs.searchGraph
import com.zoksh.buyzone.navigation.graphs.splashGraph
import com.zoksh.buyzone.presentation.MainUiEvent
import com.zoksh.buyzone.presentation.MainViewModel
import com.zoksh.core_common.presentation.mvi.ObserveAsEvents
import com.zoksh.feature_authentication.presentation.navigation.AuthDestination
import com.zoksh.feature_splash.presentation.navigation.SplashDestination


@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    snackBarHostState: SnackbarHostState,
    callbackManager: CallbackManager,
    innerPadding: PaddingValues
) {
    ObserveAsEvents(viewModel.uiEvent) { event ->
        when (event) {
            MainUiEvent.NavigateToLogin -> {
                navController.navigate(AuthDestination.Login) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = SplashDestination.Splash,
    ) {
        splashGraph(
            navController = navController,
            onShowBottomBar = viewModel::setBottomBarVisible
        )

        onBoardingGraph(
            navController = navController,
            onShowBottomBar = viewModel::setBottomBarVisible
        )

        authGraph(
            navController = navController,
            snackBarHostState = snackBarHostState,
            callbackManager = callbackManager,
            innerPadding = innerPadding,
            onShowBottomBar = viewModel::setBottomBarVisible
        )

        homeGraph(
            navController = navController,
            innerPadding = innerPadding,
            onShowBottomBar = viewModel::setBottomBarVisible
        )

        searchGraph(
            navController = navController,
            innerPadding = innerPadding,
            onShowBottomBar = viewModel::setBottomBarVisible
        )
    }
}
