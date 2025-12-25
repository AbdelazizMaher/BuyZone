package com.zoksh.feature_authentication.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.feature_authentication.R

@Preview(showBackground = true)
@Composable
fun SocialAuthSection(
    onGoogleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        SocialIconButton(
            icon = painterResource(id = R.drawable.google),
            contentDescription = "Continue with Google",
            onClick = onGoogleClick
        )

        Spacer(modifier = Modifier.width(16.dp))

        SocialIconButton(
            icon = painterResource(id = R.drawable.facebook),
            contentDescription = "Continue with Facebook",
            onClick = onFacebookClick
        )
    }
}


@Composable
fun SocialIconButton(
    icon: Painter,
    contentDescription: String?,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(56.dp)
            .clickable(onClick = onClick),
        shape = CircleShape,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                modifier = Modifier.size(24.dp),
                contentDescription = contentDescription,
                tint = Color.Unspecified
            )
        }
    }
}
