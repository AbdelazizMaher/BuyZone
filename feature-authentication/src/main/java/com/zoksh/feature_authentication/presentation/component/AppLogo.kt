package com.zoksh.feature_authentication.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zoksh.core_common.R as CoreR

@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
    name: String = "BuyZone",
    size: AppLogoSize = AppLogoSize.MEDIUM
) {
    val colors = MaterialTheme.colorScheme

    val iconSize = when (size) {
        AppLogoSize.SMALL -> 32.dp
        AppLogoSize.MEDIUM -> 48.dp
        AppLogoSize.LARGE -> 64.dp
    }

    val textStyle = when (size) {
        AppLogoSize.SMALL -> MaterialTheme.typography.titleMedium
        AppLogoSize.MEDIUM -> MaterialTheme.typography.headlineSmall
        AppLogoSize.LARGE -> MaterialTheme.typography.headlineMedium
    }

    val spacing = when (size) {
        AppLogoSize.SMALL -> 8.dp
        AppLogoSize.MEDIUM -> 12.dp
        AppLogoSize.LARGE -> 16.dp
    }

    val cornerRadius = when (size) {
        AppLogoSize.SMALL -> 8.dp
        AppLogoSize.MEDIUM -> 12.dp
        AppLogoSize.LARGE -> 16.dp
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.wrapContentSize()
    ) {
        // Icon with Gradient Background and Shadow
        Box(
            modifier = Modifier
                .size(iconSize)
                .shadow(
                    elevation = 6.dp,
                    shape = RoundedCornerShape(cornerRadius),
                    ambientColor = colors.primary.copy(alpha = 0.5f),
                    spotColor = colors.primary
                )
                .clip(RoundedCornerShape(cornerRadius))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            colors.primary,
                            colors.secondary
                        )
                    )
                )
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(cornerRadius)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = CoreR.drawable.splash_logo),
                contentDescription = null,
                modifier = Modifier.size(iconSize * 0.6f)
            )
        }
        
        Spacer(modifier = Modifier.width(spacing))
        
        Text(
            text = name,
            color = colors.onBackground,
            style = textStyle.copy(
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 0.5.sp
            ),
            maxLines = 1
        )
    }
}

enum class AppLogoSize {
    SMALL,
    MEDIUM,
    LARGE
}
