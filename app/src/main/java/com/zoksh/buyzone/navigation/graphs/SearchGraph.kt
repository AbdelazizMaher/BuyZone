package com.zoksh.buyzone.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.zoksh.feature_search.presentation.navigation.SearchDestination
import com.zoksh.feature_search.presentation.screen.SearchScreen

fun NavGraphBuilder.searchGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onShowBottomBar: (Boolean) -> Unit
) {
    composable<SearchDestination.Search> {
        onShowBottomBar(false)
        SearchScreen(
            innerPadding = innerPadding
        )
    }
}