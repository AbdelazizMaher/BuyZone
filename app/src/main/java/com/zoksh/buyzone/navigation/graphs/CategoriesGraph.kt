package com.zoksh.buyzone.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zoksh.buyzone.navigation.handlers.categories.CategoriesNavHandler
import com.zoksh.feature_categories.presentation.navigation.CategoriesDestination
import com.zoksh.feature_categories.presentation.screen.CategoriesScreen
import com.zoksh.feature_categories.presentation.viewmodel.CategoriesViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.categoriesGraph(
    navController: NavController,
    innerPadding: PaddingValues,
    onShowBottomBar: (Boolean) -> Unit
) {
    composable<CategoriesDestination.Categories> {
        onShowBottomBar(true)
        val viewModel: CategoriesViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        
        CategoriesNavHandler(
            navController = navController,
            viewModel = viewModel
        )
        
        CategoriesScreen(
            state = state,
            onIntent = viewModel::handleIntent,
            innerPadding = innerPadding
        )
    }
}
