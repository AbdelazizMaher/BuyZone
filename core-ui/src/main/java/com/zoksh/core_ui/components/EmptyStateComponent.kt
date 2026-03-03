package com.zoksh.core_ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_ui.theme.BuyZoneTheme


@Composable
fun EmptyStateComponent(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    imageRes: Int? = null,
    icon: ImageVector? = null,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    BoxWithConstraints(modifier = modifier) {
        val isCompact = maxHeight < 400.dp || maxWidth < 300.dp

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(if (isCompact) 16.dp else 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (imageRes != null) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier.size(if (isCompact) 100.dp else 180.dp)
                )
            } else if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(if (isCompact) 48.dp else 80.dp),
                    tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                )
            }

            Spacer(Modifier.height(if (isCompact) 12.dp else 24.dp))

            Text(
                text = title,
                style = if (isCompact) MaterialTheme.typography.titleMedium else MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = description,
                style = if (isCompact) MaterialTheme.typography.bodySmall else MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = if (isCompact) 8.dp else 16.dp)
            )

            if (actionText != null && onActionClick != null) {
                Spacer(Modifier.height(if (isCompact) 16.dp else 32.dp))
                if (isCompact) {
                    TextButton(onClick = onActionClick) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(actionText, fontWeight = FontWeight.Bold)
                    }
                } else {
                    PrimaryActionButton(
                        text = actionText,
                        onClick = onActionClick,
                        modifier = Modifier.widthIn(min = 200.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Large Container (Screen)")
@Composable
private fun FullScreenPreview() {
    BuyZoneTheme {
        Surface(Modifier.fillMaxSize()) {
            EmptyStateComponent(
                title = "No Internet Connection",
                description = "Check your connection and try again to browse the latest deals.",
                actionText = "Try Again",
                onActionClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Small Container (Section)")
@Composable
private fun SmallSectionPreview() {
    BuyZoneTheme {
        Surface(Modifier.size(width = 300.dp, height = 250.dp)) {
            EmptyStateComponent(
                title = "No products found",
                description = "Try adjusting your filters.",
                actionText = "Clear Filters",
                onActionClick = {}
            )
        }
    }
}
