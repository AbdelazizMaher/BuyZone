package com.zoksh.feature_cart.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_ui.components.SummaryRow
import com.zoksh.core_ui.theme.BuyZoneTheme

@Composable
fun OrderSummaryCard(
    subtotal: Double,
    total: Double,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Order Summary",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            SummaryRow(
                label = "Subtotal",
                value = "$${String.format("%.2f", subtotal)}"
            )

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
            )
            Spacer(modifier = Modifier.height(12.dp))

            SummaryRow(
                label = "Total",
                value = "$${String.format("%.2f", total)}",
                labelStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                valueStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold)
            )
        }
    }
}

@Preview(showBackground = true, name = "Order Summary")
@Composable
private fun OrderSummaryPreview() {
    BuyZoneTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            OrderSummaryCard(
                subtotal = 1831.0,
                total = 1831.0
            )
        }
    }
}
