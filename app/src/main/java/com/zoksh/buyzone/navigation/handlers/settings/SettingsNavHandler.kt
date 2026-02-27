package com.zoksh.buyzone.navigation.handlers.settings

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.zoksh.core_common.presentation.mvi.ObserveAsEvents
import com.zoksh.feature_authentication.presentation.navigation.AuthDestination
import com.zoksh.feature_settings.presentation.contract.SettingsContract
import com.zoksh.feature_settings.presentation.viewmodel.SettingsViewModel

@Composable
fun SettingsNavHandler(
    navController: NavController,
    viewModel: SettingsViewModel
) {
    ObserveAsEvents(viewModel.effect) { effect ->
        when (effect) {
            SettingsContract.Effect.NavigateToOrders -> {

            }
            SettingsContract.Effect.NavigateToAddresses -> {

            }
            SettingsContract.Effect.NavigateToWishlist -> {

            }
            SettingsContract.Effect.NavigateToLogin -> {
                navController.navigate(AuthDestination.Login)
            }
            is SettingsContract.Effect.ShowMessage -> {

            }
        }
    }
}
