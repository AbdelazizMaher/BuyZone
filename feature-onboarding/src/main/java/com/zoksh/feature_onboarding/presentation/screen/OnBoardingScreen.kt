package com.zoksh.feature_onboarding.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zoksh.feature_onboarding.presentation.component.AppLogo
import com.zoksh.feature_onboarding.presentation.component.OnBoardingBackground
import com.zoksh.feature_onboarding.presentation.component.OnBoardingButtons
import com.zoksh.feature_onboarding.presentation.component.OnBoardingIndicator
import com.zoksh.feature_onboarding.presentation.component.OnBoardingText
import com.zoksh.feature_onboarding.presentation.contract.OnBoardingContract
import com.zoksh.feature_onboarding.presentation.viewmodel.OnBoardingViewModel

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(
        initialPage = state.pageNo,
        pageCount = { state.pages.size }
    )

    LaunchedEffect(pagerState.currentPage) {
        viewModel.handleIntent(OnBoardingContract.Intent.PageChanged(pagerState.currentPage))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { }
        OnBoardingBackground(
            imageRes = state.currentPageData.imageRes,
            color = state.currentPageData.color
        )
        AppLogo(
            modifier = Modifier
                .statusBarsPadding()
                .padding(start = 24.dp, top = 12.dp)
                .align(Alignment.TopStart)
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(horizontal = 24.dp, vertical = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OnBoardingText(
                title = state.currentPageData.title,
                description = state.currentPageData.description
            )
            Spacer(modifier = Modifier.height(24.dp))
            OnBoardingIndicator(
                pageCount = state.pages.size,
                currentPage = state.pageNo
            )
            Spacer(modifier = Modifier.height(36.dp))
            OnBoardingButtons(
                isLastPage = state.currentPageData.isLastPage,
                color = state.currentPageData.color,
                onNext = {
                    viewModel.handleIntent(OnBoardingContract.Intent.Next)
                },
                onSkip = {
                    viewModel.handleIntent(OnBoardingContract.Intent.Skip)
                },
                onGetStarted = {
                    viewModel.handleIntent(OnBoardingContract.Intent.GetStarted)
                }
            )
        }
    }
}