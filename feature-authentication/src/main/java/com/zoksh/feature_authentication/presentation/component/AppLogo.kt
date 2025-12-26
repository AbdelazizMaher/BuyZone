package com.zoksh.feature_authentication.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zoksh.feature_authentication.R

@Composable
fun AppLogo(
    name: String = "BuyZone",
    size: AppLogoSize = AppLogoSize.MEDIUM
) {
    val colors = MaterialTheme.colorScheme

    val (iconSize, textStyle, spacing) = when (size) {
        AppLogoSize.SMALL -> Triple(
            24.dp,
            MaterialTheme.typography.headlineMedium,
            8.dp
        )

        AppLogoSize.MEDIUM -> Triple(
            36.dp,
            MaterialTheme.typography.titleMedium,
            8.dp
        )

        AppLogoSize.LARGE -> Triple(
            48.dp,
            MaterialTheme.typography.headlineLarge,
            8.dp
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.wrapContentSize()
    ) {
        Box(
            modifier = Modifier
                .size(iconSize)
                .clip(RoundedCornerShape(12.dp))
                .background(colors.primary),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = null,
                modifier = Modifier.size(iconSize)
            )
        }
        Spacer(modifier = Modifier.width(spacing))
        Text(
            text = name,
            color = colors.onBackground,
            style = textStyle,
            maxLines = 1
        )
    }
}

enum class AppLogoSize {
    SMALL,
    MEDIUM,
    LARGE
}