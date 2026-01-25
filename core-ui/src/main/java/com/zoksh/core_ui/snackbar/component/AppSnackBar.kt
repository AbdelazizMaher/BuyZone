package com.zoksh.core_ui.snackbar.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.zoksh.core_ui.snackbar.mapper.visualsFor
import com.zoksh.core_ui.snackbar.model.AppMessage
import com.zoksh.core_ui.snackbar.model.AppMessageAction
import com.zoksh.core_ui.snackbar.model.MessageType
import com.zoksh.core_ui.theme.BuyZoneTheme

@Composable
fun AppSnackBar(
    message: AppMessage,
    onDismiss: () -> Unit = {}
) {
    val visuals = visualsFor(message.type)

    Surface(
        tonalElevation = 8.dp,
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .heightIn(min = 56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(visuals.accent)
            )
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = visuals.icon,
                    contentDescription = null,
                    tint = visuals.accent
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )

                if (message.action != null) {
                    TextButton(onClick = message.action.onClick) {
                        Text(message.action.label)
                    }
                } else {
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Dismiss",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
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
private fun AppSnackBarPreview(
    @PreviewParameter(AppMessagePreviewParameterProvider::class) message: AppMessage
) {
    BuyZoneTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            AppSnackBar(message = message)
        }
    }
}

private class AppMessagePreviewParameterProvider : PreviewParameterProvider<AppMessage> {
    override val values = sequenceOf(
        AppMessage(
            text = "Success! Your profile has been updated.",
            type = MessageType.SUCCESS
        ),
        AppMessage(
            text = "An error occurred while saving.",
            type = MessageType.ERROR,
            action = AppMessageAction("Retry") {}
        ),
        AppMessage(
            text = "Your subscription is about to expire.",
            type = MessageType.WARNING
        ),
        AppMessage(
            text = "Did you know you can save items to your wishlist?",
            type = MessageType.INFO
        )
    )
}
