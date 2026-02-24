package com.zoksh.buyzone.navigation.handlers.categories

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.zoksh.core_common.presentation.mvi.ObserveAsEvents
import com.zoksh.feature_categories.presentation.contract.CategoriesContract
import com.zoksh.feature_categories.presentation.viewmodel.CategoriesViewModel
import com.zoksh.feature_details.presentation.navigation.DetailsDestination

@Composable
fun CategoriesNavHandler(
    navController: NavController,
    viewModel: CategoriesViewModel
) {
    ObserveAsEvents(viewModel.effect) { effect ->
        when (effect) {
            CategoriesContract.Effect.NavigateBack -> {
                navController.popBackStack()
            }
            is CategoriesContract.Effect.NavigateToDetails -> {
                navController.navigate(DetailsDestination.Details(effect.productId))
            }
        }
    }
}
