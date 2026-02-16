package com.zoksh.buyzone.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.zoksh.buyzone.navigation.handlers.home.HomeNavHandler
import com.zoksh.feature_home.presentation.navigation.HomeDestination
import com.zoksh.feature_home.presentation.screen.HomeScreen
import com.zoksh.feature_home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onShowBottomBar: (Boolean) -> Unit
) {
    composable<HomeDestination.Home> {
        onShowBottomBar(true)
        val viewModel: HomeViewModel = koinViewModel()
        HomeNavHandler(navController, viewModel)
        HomeScreen(
            state = viewModel.state.collectAsStateWithLifecycle().value,
            onIntent = viewModel::handleIntent,
            innerPadding = innerPadding
        )
    }
}
