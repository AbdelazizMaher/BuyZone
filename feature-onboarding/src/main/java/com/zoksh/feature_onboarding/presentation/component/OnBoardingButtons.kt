package com.zoksh.feature_onboarding.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OnBoardingButtons(
    isLastPage: Boolean = false,
    color: Color,
    onNext: () -> Unit = {},
    onSkip: () -> Unit = {},
    onGetStarted: () -> Unit = {},
) {
    if (isLastPage) {
        Button(
            onClick = onGetStarted,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = color,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Get Started",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = onSkip,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = color,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Skip",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Button(
                onClick = onNext,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = color,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Next →",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}