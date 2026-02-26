package com.zoksh.buyzone.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zoksh.buyzone.navigation.handlers.settings.SettingsNavHandler
import com.zoksh.feature_settings.presentation.navigation.SettingsDestination
import com.zoksh.feature_settings.presentation.screen.SettingsScreen
import com.zoksh.feature_settings.presentation.viewmodel.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.settingsGraph(
    navController: NavController,
    innerPadding: PaddingValues,
    onShowBottomBar: (Boolean) -> Unit
) {
    composable<SettingsDestination.Settings> {
        onShowBottomBar(true)
        val viewModel: SettingsViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        
        SettingsNavHandler(
            navController = navController,
            viewModel = viewModel
        )
        
        SettingsScreen(
            state = state,
            onIntent = viewModel::handleIntent,
            innerPadding = innerPadding
        )
    }
}
