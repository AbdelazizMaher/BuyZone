package com.zoksh.buyzone.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.zoksh.buyzone.navigation.handlers.search.SearchNavHandler
import com.zoksh.feature_search.presentation.navigation.SearchDestination
import com.zoksh.feature_search.presentation.screen.SearchScreen
import com.zoksh.feature_search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.searchGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onShowBottomBar: (Boolean) -> Unit
) {
    composable<SearchDestination.Search> {
        onShowBottomBar(false)
        val viewModel: SearchViewModel = koinViewModel()

        SearchNavHandler(navController, viewModel)

        SearchScreen(
            state = viewModel.state.collectAsStateWithLifecycle().value,
            onIntent = viewModel::handleIntent,
            innerPadding = innerPadding
        )
    }
}