package com.zoksh.feature_details.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_common.R
import com.zoksh.core_ui.components.PrimaryActionButton
import com.zoksh.core_ui.theme.BuyZoneTheme

@Composable
fun DetailsBottomBar(
    price: String,
    onAddToCartClick: () -> Unit,
    modifier: Modifier = Modifier,
    bottomPadding: androidx.compose.ui.unit.Dp = 0.dp
) {
    val topGradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, MaterialTheme.colorScheme.background)
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(topGradient)
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background,
            shadowElevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .padding(bottom = bottomPadding)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Price :",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = price,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                PrimaryActionButton(
                    text = "Add To Cart",
                    icon = R.drawable.ic_cart,
                    onClick = onAddToCartClick,
                    modifier = Modifier.fillMaxWidth(0.75f)
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun DetailsBottomBarPreview() {
    BuyZoneTheme {
        DetailsBottomBar(
            price = "$290.00",
            onAddToCartClick = {}
        )
    }
}
