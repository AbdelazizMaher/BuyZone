package com.zoksh.buyzone.navigation.handlers.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.zoksh.feature_details.presentation.navigation.DetailsDestination
import com.zoksh.feature_search.presentation.contract.SearchContract
import com.zoksh.feature_search.presentation.viewmodel.SearchViewModel

@Composable
fun SearchNavHandler(
    navController: NavHostController,
    viewModel: SearchViewModel,
) {
    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                SearchContract.Effect.NavigateBack -> {
                    navController.popBackStack()
                }
                is SearchContract.Effect.NavigateToDetails -> {
                    navController.navigate(DetailsDestination.Details(effect.productId))
                }
            }
        }
    }
}