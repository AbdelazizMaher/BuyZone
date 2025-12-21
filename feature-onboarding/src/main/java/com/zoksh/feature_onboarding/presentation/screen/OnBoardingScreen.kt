package com.zoksh.feature_onboarding.presentation.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zoksh.feature_onboarding.R
import com.zoksh.feature_onboarding.presentation.viewmodel.OnBoardingViewModel

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(
        initialPage = state.pageNo,
        pageCount = { state.pages.size }
    )

    LaunchedEffect(pagerState.currentPage) {

    }

    Box(modifier = Modifier.fillMaxSize()) {
        OnBoardingBackground(
            imageRes = state.currentPageData.imageRes,
            color = state.currentPageData.color
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            AppLogo()
            Spacer(modifier = Modifier.weight(1f))
            OnBoardingText(
                title = state.currentPageData.title,
                description = state.currentPageData.description
            )
            Spacer(modifier = Modifier.height(24.dp))
            OnBoardingIndicator(
                pageCount = state.pages.size,
                currentPage = state.pageNo
            )
            Spacer(modifier = Modifier.height(24.dp))
            OnBoardingButtons(
                isLastPage = state.currentPageData.isLastPage,
                color = state.currentPageData.color,
                onNext = {

                },
                onSkip = {

                },
                onGetStarted = {

                }
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { }
        }
    }
}


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

@Composable
fun AppLogo(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(
                Color.Black.copy(alpha = 0.35f)
            )
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.15f),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.app_logo_1),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(35.dp)
                    .clip(RoundedCornerShape(50))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "BuyZone",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

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

@Composable
fun OnBoardingIndicator(
    pageCount: Int,
    currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage
            val width by animateDpAsState(
                targetValue = if (isSelected) 24.dp else 8.dp,
            )
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .width(width)
                    .clip(RoundedCornerShape(2.dp))
                    .background(
                        if (isSelected) Color.White
                        else Color.White.copy(alpha = 0.4f)
                    )
            )
        }
    }
}

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