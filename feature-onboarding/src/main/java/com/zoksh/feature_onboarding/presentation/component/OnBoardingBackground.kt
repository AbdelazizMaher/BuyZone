package com.zoksh.feature_onboarding.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun OnBoardingBackground(
    imageRes: Int,
    color: Color
) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to color.copy(alpha = 0.75f),
                        0.55f to color.copy(alpha = 0.45f),
                        0.75f to Color.Black.copy(alpha = 0.6f),
                        1.0f to Color.Black
                    )
                )
            )
    )
}