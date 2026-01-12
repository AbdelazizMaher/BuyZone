package com.zoksh.buyzone.navigation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.zoksh.feature_home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeNavHandler(
    navController: NavHostController,
    viewModel: HomeViewModel,
) {
    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {

                else -> {}
            }

        }

    }
}