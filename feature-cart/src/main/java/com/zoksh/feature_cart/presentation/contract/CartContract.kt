package com.zoksh.feature_cart.presentation.contract

import androidx.compose.runtime.Immutable
import com.zoksh.core_common.presentation.ui_state.UiState
import com.zoksh.feature_cart.presentation.model.CartItemUiModel

sealed interface CartContract {

    @Immutable
    data class State(
        val cartItemsState: UiState<List<CartItemUiModel>> = UiState.Loading,
        val promoCode: String = "",
        val subtotal: Double = 0.0,
        val total: Double = 0.0
    )

    sealed interface Intent {
        data object LoadCart : Intent
        data class OnQuantityChange(val itemId: String, val newQuantity: Int) : Intent
        data class OnRemoveItem(val itemId: String) : Intent
        data class OnPromoCodeChange(val code: String) : Intent
        data object OnApplyPromoCode : Intent
        data object OnCheckoutClick : Intent
    }

    sealed interface Effect {
        data object NavigateToCheckout : Effect
        data class ShowError(val message: String) : Effect
    }
}
