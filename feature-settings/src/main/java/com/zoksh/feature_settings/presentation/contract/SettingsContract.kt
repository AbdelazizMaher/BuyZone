package com.zoksh.feature_settings.presentation.contract

import androidx.compose.runtime.Immutable
import com.zoksh.core_common.presentation.ui_state.UiState

sealed interface SettingsContract {

    @Immutable
    data class State(
        val profileState: UiState<UserUiModel> = UiState.Loading,
        val isGuest: Boolean = true,
        val appTheme: String = "Light",
        val currency: String = "USD"
    )

    sealed interface Intent {
        data object LoadProfile : Intent
        data object Orders : Intent
        data object Addresses : Intent
        data object Wishlist : Intent
        data object CurrencySelection : Intent
        data object Notifications : Intent
        data object AppTheme : Intent
        data object AboutUs : Intent
        data object HelpContact : Intent
        data object Logout : Intent
        data object LoginRegister : Intent
    }

    sealed interface Effect {
        data object NavigateToOrders : Effect
        data object NavigateToAddresses : Effect
        data object NavigateToWishlist : Effect
        data object NavigateToLogin : Effect
        data class ShowMessage(val message: String) : Effect
    }
}

@Immutable
data class UserUiModel(
    val name: String,
    val email: String,
    val imageUrl: String? = null
)
