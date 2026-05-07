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
                CartItemUiModel("1", "p1", "Nike Air Max 270", "https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&fit=crop&q=80&w=200", 150.0, 180.0, 8, 1, "Red", "42"),
                CartItemUiModel("2", "p2", "Premium White Tee", "https://images.unsplash.com/photo-1620799140408-edc6dcb6d633?auto=format&fit=crop&q=80&w=200", 35.0, 45.0, null, 2, "White", "L"),
                CartItemUiModel("3", "p3", "Premium Silver Watch", "https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&q=80&w=200", 199.0, 250.0, 31, 1, "Silver"),
                CartItemUiModel("4", "p4", "Puma RS-X Bold", "https://images.unsplash.com/photo-1608231387042-66d1773070a5?auto=format&fit=crop&q=80&w=200", 110.0, 130.0, null, 1, "Black", "43")
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
