package com.zoksh.feature_cart.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class CartItemUiModel(
    val id: String,
    val productId: String,
    val name: String,
    val imageUrl: String,
    val price: Double,
    val originalPrice: Double? = null,
    val discountPercent: Int? = null,
    val quantity: Int,
    val color: String? = null,
    val size: String? = null,
    val isOutOfStock: Boolean = false
) {
    val totalPrice: Double get() = price * quantity
}
