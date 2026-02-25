package com.zoksh.feature_cart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoksh.core_common.presentation.ui_state.UiState
import com.zoksh.feature_cart.presentation.contract.CartContract
import com.zoksh.feature_cart.presentation.model.CartItemUiModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private val _state = MutableStateFlow(CartContract.State())
    val state = _state.asStateFlow()

    private val _effect = Channel<CartContract.Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        handleIntent(CartContract.Intent.LoadCart)
    }

    fun handleIntent(intent: CartContract.Intent) {
        when (intent) {
            CartContract.Intent.LoadCart -> loadCartItems()
            is CartContract.Intent.OnQuantityChange -> updateQuantity(intent.itemId, intent.newQuantity)
            is CartContract.Intent.OnRemoveItem -> removeItem(intent.itemId)
            is CartContract.Intent.OnPromoCodeChange -> _state.update { it.copy(promoCode = intent.code) }
            CartContract.Intent.OnApplyPromoCode -> applyPromoCode()
            CartContract.Intent.OnCheckoutClick -> {
                viewModelScope.launch { _effect.send(CartContract.Effect.NavigateToCheckout) }
            }
        }
    }

    private fun loadCartItems() {
        viewModelScope.launch {
            _state.update { it.copy(cartItemsState = UiState.Loading) }
            
            val mockItems = listOf(
                CartItemUiModel("1", "p1", "Galaxy S24 Ultra", "https://picsum.photos/200/200?random=1", 1199.0, 1299.0, 8, 1, "Titanium Gray", "256GB"),
                CartItemUiModel("2", "p2", "AirPods Pro 2", "https://picsum.photos/200/200?random=2", 249.0, null, null, 2),
                CartItemUiModel("3", "p3", "Leather Wallet", "https://picsum.photos/200/200?random=3", 89.0, 129.0, 31, 1, "Brown"),
                CartItemUiModel("4", "p4", "Wireless Charger", "https://picsum.photos/200/200?random=4", 49.0, null, null, 1, null, null, true)
            )
            
            updateTotals(mockItems)
        }
    }

    private fun updateQuantity(itemId: String, quantity: Int) {
        if (quantity < 1) return
        val currentState = _state.value.cartItemsState
        if (currentState is UiState.Success) {
            val updatedItems = currentState.data.map {
                if (it.id == itemId) it.copy(quantity = quantity) else it
            }
            updateTotals(updatedItems)
        }
    }

    private fun removeItem(itemId: String) {
        val currentState = _state.value.cartItemsState
        if (currentState is UiState.Success) {
            val updatedItems = currentState.data.filter { it.id != itemId }
            updateTotals(updatedItems)
        }
    }

    private fun applyPromoCode() {  }

    private fun updateTotals(items: List<CartItemUiModel>) {
        val subtotal = items.filter { !it.isOutOfStock }.sumOf { it.totalPrice }

        _state.update {
            it.copy(
                cartItemsState = if (items.isEmpty()) UiState.Empty else UiState.Success(items),
                subtotal = subtotal,
                total = subtotal
            )
        }
    }
}
