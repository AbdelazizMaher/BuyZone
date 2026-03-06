package com.zoksh.feature_cart.presentation.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_common.presentation.ui_state.UiState
import com.zoksh.core_ui.components.AppHeader
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.feature_cart.presentation.components.CartBottomActionBar
import com.zoksh.feature_cart.presentation.components.CartItemCard
import com.zoksh.feature_cart.presentation.components.CartShimmer
import com.zoksh.feature_cart.presentation.components.OrderSummaryCard
import com.zoksh.feature_cart.presentation.components.PromoCodeSection
import com.zoksh.feature_cart.presentation.contract.CartContract
import com.zoksh.feature_cart.presentation.model.CartItemUiModel

@Composable
fun CartScreen(
    state: CartContract.State,
    onIntent: (CartContract.Intent) -> Unit,
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = innerPadding.calculateTopPadding())
    ) {
        AppHeader(
            title = "Shopping Cart",
            modifier = Modifier.fillMaxWidth(),
        )

        Box(modifier = Modifier.weight(1f)) {
            when (val cartItemsState = state.cartItemsState) {
                is UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item {
                            Text(
                                text = "Cart Items (${cartItemsState.data.size})",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        items(cartItemsState.data, key = { it.id }) { item ->
                            CartItemCard(
                                item = item,
                                onQuantityChange = {
                                    onIntent(
                                        CartContract.Intent.OnQuantityChange(
                                            item.id,
                                            it
                                        )
                                    )
                                },
                                onRemove = { onIntent(CartContract.Intent.OnRemoveItem(item.id)) }
                            )
                        }

                        item {
                            PromoCodeSection(
                                promoCode = state.promoCode,
                                onPromoCodeChange = {
                                    onIntent(
                                        CartContract.Intent.OnPromoCodeChange(
                                            it
                                        )
                                    )
                                },
                                onApplyClick = { onIntent(CartContract.Intent.OnApplyPromoCode) }
                            )
                        }

                        item {
                            OrderSummaryCard(
                                subtotal = state.subtotal,
                                total = state.total
                            )
                        }

                        item { Spacer(Modifier.height(80.dp)) }
                    }
                }

                is UiState.Loading -> {
                    CartShimmer()
                }
                is UiState.Empty -> {}
                is UiState.Error -> {}
            }
        }

        if (state.cartItemsState is UiState.Success) {
            CartBottomActionBar(
                totalAmount = state.total,
                onCheckoutClick = { onIntent(CartContract.Intent.OnCheckoutClick) },
                bottomPadding = innerPadding.calculateBottomPadding()
            )
        }
    }
}

@Preview(showBackground = true, name = "Cart Screen Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Cart Screen Dark")
@Composable
private fun CartScreenPreview() {
    val mockItems = listOf(
        CartItemUiModel(
            "1",
            "p1",
            "Galaxy S24 Ultra",
            "",
            1199.0,
            1299.0,
            8,
            1,
            "Titanium Gray",
            "256GB"
        ),
        CartItemUiModel("2", "p2", "AirPods Pro 2", "", 249.0, null, null, 2),
        CartItemUiModel("3", "p3", "Leather Wallet", "", 89.0, 129.0, 31, 1, "Brown"),
        CartItemUiModel("4", "p4", "Wireless Charger", "", 49.0, null, null, 1, null, null, true)
    )

    BuyZoneTheme {
        CartScreen(
            state = CartContract.State(
                cartItemsState = UiState.Success(mockItems),
                subtotal = 1831.0,
                total = 1831.0
            ),
            onIntent = {},
            innerPadding = PaddingValues(0.dp)
        )
    }
}
