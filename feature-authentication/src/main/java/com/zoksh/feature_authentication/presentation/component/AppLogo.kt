package com.zoksh.feature_authentication.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

    val (iconSize, textStyle, spacing, cornerRadius) = when (size) {
        AppLogoSize.SMALL -> Quadruple(
            32.dp,
            MaterialTheme.typography.titleMedium,
            8.dp,
            8.dp
        )
        AppLogoSize.MEDIUM -> Quadruple(
            44.dp,
            MaterialTheme.typography.headlineSmall,
            12.dp,
            12.dp
        )
        AppLogoSize.LARGE -> Quadruple(
            56.dp,
            MaterialTheme.typography.headlineMedium,
            14.dp,
            14.dp
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.wrapContentSize()
    ) {
        Box(
            modifier = Modifier
                .size(iconSize)
                .clip(RoundedCornerShape(cornerRadius))
                .background(colors.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = CoreR.drawable.splash_logo),
                contentDescription = null,
                modifier = Modifier.size(iconSize * 0.65f)
            )
        }
        
        Spacer(modifier = Modifier.width(spacing))
        
        Text(
            text = name,
            color = colors.onBackground,
            style = textStyle.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.2.sp
            ),
            maxLines = 1
        )
    }
}

private data class Quadruple<A, B, C, D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
)

enum class AppLogoSize {
    SMALL,
    MEDIUM,
    LARGE
}
