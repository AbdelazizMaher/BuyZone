package com.zoksh.feature_authentication.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun OptionsRow(
    isCheck: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val colors = MaterialTheme.colorScheme

        RoundedCheckbox(
            checked = isCheck,
            onCheckedChange = onCheckedChange,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "Remember me",
            style = MaterialTheme.typography.bodyMedium,
            color = colors.onBackground
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Forgot Password?",
            textAlign = TextAlign.End,
            modifier = Modifier.clickable { onForgotPasswordClick() },
            style = MaterialTheme.typography.bodyMedium,
            color = colors.primary
        )

    }
}

@Composable
fun RoundedCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    Surface(
        modifier = modifier
            .size(24.dp)
            .clickable { onCheckedChange(!checked) },
        shape = RoundedCornerShape(6.dp),
        color = if (checked) colors.primary else Color.Transparent,
        border = BorderStroke(
            1.5.dp,
            if (checked) colors.primary else colors.outline
        )
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = colors.onPrimary,
                modifier = Modifier.padding(3.dp)
            )
        }
    }
}
