package com.zoksh.buyzone.navigation.handlers.cart

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.zoksh.core_common.presentation.mvi.ObserveAsEvents
import com.zoksh.feature_cart.presentation.contract.CartContract
import com.zoksh.feature_cart.presentation.viewmodel.CartViewModel

@Composable
fun CartNavHandler(
    navController: NavController,
    viewModel: CartViewModel
) {
    ObserveAsEvents(viewModel.effect) { effect ->
        when (effect) {
            CartContract.Effect.NavigateToCheckout -> {
            }
            is CartContract.Effect.ShowError -> {
            }
        }
    }
}
