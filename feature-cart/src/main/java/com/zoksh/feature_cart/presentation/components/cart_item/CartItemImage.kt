package com.zoksh.feature_cart.presentation.components.cart_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zoksh.core_ui.components.DiscountBadge
import androidx.compose.material3.MaterialTheme

@Composable
fun CartItemImage(
    imageUrl: String,
    name: String,
    discountPercent: Int?,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.size(90.dp)) {
        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
        )
        
        discountPercent?.let {
            DiscountBadge(
                percent = it,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = (-4).dp, y = (-4).dp)
            )
        }
    }
}
