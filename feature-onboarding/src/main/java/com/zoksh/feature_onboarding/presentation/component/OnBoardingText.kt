package com.zoksh.feature_onboarding.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnBoardingText(
    title: String = "jksdfkdlsgdhljkfsld",
    description: String = "kjnfglsdfngsdfmgnanmsdnflsfkjasldkf"
) {
    Column {
        Text(
            text = title,
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 40.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = description,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 16.sp
        )
    }
}