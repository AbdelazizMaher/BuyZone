package com.zoksh.buyzone.navigation.handlers.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.zoksh.feature_categories.presentation.navigation.CategoriesDestination
import com.zoksh.feature_details.presentation.navigation.DetailsDestination
import com.zoksh.feature_home.presentation.contract.HomeContract
import com.zoksh.feature_home.presentation.viewmodel.HomeViewModel
import com.zoksh.feature_search.presentation.navigation.SearchDestination

@Composable
fun HomeNavHandler(
    navController: NavHostController,
    viewModel: HomeViewModel,
) {
    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                HomeContract.Effect.NavigateToAllCategories -> {
                    navController.navigate(CategoriesDestination.Categories)
                }
                HomeContract.Effect.NavigateToAllTrending -> {

                }
                is HomeContract.Effect.NavigateToBrand -> {

                }
                is HomeContract.Effect.NavigateToCategory -> {

                }
                is HomeContract.Effect.NavigateToProduct -> {
                    navController.navigate(DetailsDestination.Details(effect.productId))
                }
                HomeContract.Effect.NavigateToSearch -> {
                    navController.navigate(SearchDestination.Search)
                }
                is HomeContract.Effect.ShowMessage -> {

                }
            }
        }
    }
}
