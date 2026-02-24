package com.zoksh.buyzone.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
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
        
        CategoriesNavHandler(
            navController = navController,
            viewModel = viewModel
        )
        
        CategoriesScreen(
            innerPadding = innerPadding,
            viewModel = viewModel
        )
    }
}
