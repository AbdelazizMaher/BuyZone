package com.zoksh.feature_onboarding.presentation.model

import androidx.compose.ui.graphics.Color
import com.zoksh.feature_onboarding.R

data class OnBoardingPageUiModel(
    val title: String,
    val description: String,
    val imageRes: Int,
    val color: Color,
    val isLastPage: Boolean
)

val OnBoardingPages = listOf(
    OnBoardingPageUiModel(
        title = "Your Style,\nYour Way",
        description = "Discover thousands of premium products from top brands",
        imageRes = R.drawable.page1,
        color = Color(0xFF8B5CF6),
        isLastPage = false
    ),
    OnBoardingPageUiModel(
        title = "Shop With Confidence",
        description = "Secure payments, easy returns, 24/7 support",
        imageRes = R.drawable.page1,
        color = Color(0xFFF97316),
        isLastPage = false
    ),
    OnBoardingPageUiModel(
        title = "Ready to Start?",
        description = "Let’s find your perfect style today",
        imageRes = R.drawable.page1,
        color = Color(0xFF22C55E),
        isLastPage = true
    )
)




