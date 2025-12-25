package com.zoksh.feature_authentication.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryAction(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme
    Button(
        onClick = onClick,
        enabled = !enabled,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.primary,
            contentColor = Color.White
        ),
    ) {
        if (enabled) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium
            )
        } else {
            DotLoadingIndicator()
        }
    }
}