package com.zoksh.buyzone.navigation.handlers.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.zoksh.core_common.presentation.mvi.ObserveAsEvents
import com.zoksh.feature_details.presentation.contract.DetailsContract
import com.zoksh.feature_details.presentation.viewmodel.DetailsViewModel

@Composable
fun DetailsNavHandler(
    navController: NavController,
    viewModel: DetailsViewModel
) {
    ObserveAsEvents(viewModel.effect) { effect ->
        when (effect) {
            DetailsContract.Effect.NavigateBack -> {
                navController.popBackStack()
            }
            is DetailsContract.Effect.ShowError -> {

            }
            DetailsContract.Effect.ProductAddedToCart -> {

            }
        }
    }
}
