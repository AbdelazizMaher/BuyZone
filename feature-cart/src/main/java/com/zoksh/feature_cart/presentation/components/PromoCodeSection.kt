package com.zoksh.feature_cart.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zoksh.core_ui.components.AppInputWithAction

@Composable
fun PromoCodeSection(
    promoCode: String,
    onPromoCodeChange: (String) -> Unit,
    onApplyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        Text(
            text = "Promo code",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        AppInputWithAction(
            value = promoCode,
            onValueChange = onPromoCodeChange,
            hint = "Enter code",
            actionText = "Apply",
            onActionClick = onApplyClick,
            leadingIcon = Icons.Outlined.LocalOffer,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
