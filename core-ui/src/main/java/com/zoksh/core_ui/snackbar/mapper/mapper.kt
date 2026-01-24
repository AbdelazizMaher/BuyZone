package com.zoksh.core_ui.snackbar.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.zoksh.core_ui.snackbar.model.MessageType
import com.zoksh.core_ui.snackbar.model.MessageVisuals

@Composable
fun visualsFor(type: MessageType): MessageVisuals = when (type) {
    MessageType.SUCCESS -> MessageVisuals(
        background = MaterialTheme.colorScheme.surface,
        accent = Color(0xFF2E7D32),
        icon = Icons.Rounded.CheckCircle
    )
    MessageType.ERROR -> MessageVisuals(
        background = MaterialTheme.colorScheme.surface,
        accent = Color(0xFFD32F2F),
        icon = Icons.Rounded.Close
    )
    MessageType.WARNING -> MessageVisuals(
        background = MaterialTheme.colorScheme.surface,
        accent = Color(0xFFF9A825),
        icon = Icons.Rounded.Warning
    )
    MessageType.INFO -> MessageVisuals(
        background = MaterialTheme.colorScheme.surface,
        accent = MaterialTheme.colorScheme.primary,
        icon = Icons.Rounded.Info
    )
}