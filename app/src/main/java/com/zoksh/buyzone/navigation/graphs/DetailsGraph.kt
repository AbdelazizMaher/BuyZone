package com.zoksh.buyzone.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zoksh.buyzone.navigation.handlers.details.DetailsNavHandler
import com.zoksh.feature_details.presentation.navigation.DetailsDestination
import com.zoksh.feature_details.presentation.screen.DetailsScreen
import com.zoksh.feature_details.presentation.viewmodel.DetailsViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.detailsGraph(
    navController: NavController,
    innerPadding: PaddingValues,
    onShowBottomBar: (Boolean) -> Unit
) {
    composable<DetailsDestination.Details> {
        onShowBottomBar(false)
        val viewModel: DetailsViewModel = koinViewModel()
        
        DetailsNavHandler(navController, viewModel)
        
        DetailsScreen(
            state = viewModel.state.collectAsStateWithLifecycle().value,
            onIntent = viewModel::handleIntent,
            innerPadding = innerPadding
        )
    }
}
