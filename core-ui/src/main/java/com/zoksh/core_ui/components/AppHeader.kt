package com.zoksh.core_ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
fun AppHeader(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    trailingIcon: ImageVector? = null,
    trailingDrawable: Int? = null,
    onTrailingClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBackClick != null) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = if (onBackClick == null) 16.dp else 8.dp),
            textAlign = if (onBackClick == null && trailingIcon == null && trailingDrawable == null)
                TextAlign.Center else TextAlign.Start
        )

        if (trailingIcon != null) {
            IconButton(onClick = onTrailingClick) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        } else if (trailingDrawable != null) {
            IconButton(onClick = onTrailingClick) {
                Icon(
                    painter = painterResource(id = trailingDrawable),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(24.dp)
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
private fun AppHeaderPreview() {
    BuyZoneTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            androidx.compose.foundation.layout.Column {
                AppHeader(
                    title = "Search Products",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    onBackClick = {},
                    trailingIcon = Icons.Default.Settings
                )
                AppHeader(
                    title = "Settings",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    onBackClick = {}
                )
                AppHeader(
                    title = "Profile",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                )
            }
        }
    }
}
