package com.zoksh.feature_cart.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_ui.components.QuantitySelector
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.feature_cart.presentation.components.cart_item.CartItemImage
import com.zoksh.feature_cart.presentation.components.cart_item.CartItemInfo
import com.zoksh.feature_cart.presentation.model.CartItemUiModel

@Composable
fun CartItemCard(
    item: CartItemUiModel,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .height(IntrinsicSize.Min)
                    .then(
                        if (item.isOutOfStock) Modifier
                            .blur(12.dp)
                            .alpha(0.3f)
                        else Modifier
                    ),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CartItemImage(
                    imageUrl = item.imageUrl,
                    name = item.name,
                    discountPercent = item.discountPercent
                )

                CartItemInfo(
                    modifier = Modifier.weight(1f),
                    name = item.name,
                    price = item.price,
                    originalPrice = item.originalPrice,
                    totalPrice = item.totalPrice,
                    quantity = item.quantity,
                    size = item.size,
                    color = item.color
                )
            }

            Column(
                modifier = Modifier
                    .matchParentSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = onRemove,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(18.dp)
                    )
                }

                if (!item.isOutOfStock) {
                    QuantitySelector(
                        quantity = item.quantity,
                        onQuantityChange = onQuantityChange
                    )
                }
            }

            if (item.isOutOfStock) {
                Box(Modifier.matchParentSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Out of Stock",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFFEF5350),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Cart Item Card Preview")
@Composable
private fun CartItemCardPreview() {
    BuyZoneTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            CartItemCard(
                item = CartItemUiModel(
                    id = "1",
                    productId = "p1",
                    name = "Galaxy S24 Ultra",
                    imageUrl = "",
                    price = 1199.0,
                    originalPrice = 1299.0,
                    discountPercent = 8,
                    quantity = 1,
                    size = "256GB",
                    color = "Titanium Gray"
                ),
                onQuantityChange = {},
                onRemove = {}
            )
        }
    }
}
