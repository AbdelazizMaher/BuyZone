package com.zoksh.feature_settings.presentation.contract

import androidx.compose.runtime.Immutable

sealed interface SettingsContract {

    @Immutable
    data class State(
        val user: UserUiModel? = null,
        val isGuest: Boolean = true,
        val appTheme: String = "Light",
        val currency: String = "USD",
        val notificationCount: Int = 0
    )

    sealed interface Intent {
        data object EditProfile : Intent
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
        data object NavigateToEditProfile : Effect
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
