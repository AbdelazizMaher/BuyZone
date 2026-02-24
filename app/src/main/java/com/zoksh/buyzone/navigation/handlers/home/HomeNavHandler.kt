package com.zoksh.buyzone.navigation.handlers.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.zoksh.feature_home.presentation.contract.HomeContract
import com.zoksh.feature_home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeNavHandler(
    navController: NavHostController,
    viewModel: HomeViewModel,
) {
    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                HomeContract.Effect.NavigateToAllCategories -> {

                }
                HomeContract.Effect.NavigateToAllTrending -> {

                }
                is HomeContract.Effect.NavigateToBrand -> {

                }
                is HomeContract.Effect.NavigateToCategory -> {

                }
                is HomeContract.Effect.NavigateToProduct -> {

                }
                HomeContract.Effect.NavigateToSearch -> {

                }
                is HomeContract.Effect.ShowMessage -> {

                }
            }

        }

    }
}