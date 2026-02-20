package com.zoksh.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zoksh.core_common.presentation.ui_state.UiState
import com.zoksh.feature_home.R
import com.zoksh.feature_home.presentation.model.PromosUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CarouselPromosSection(
    modifier: Modifier = Modifier,
    state: UiState<List<PromosUiModel>>,
    onClick: (String) -> Unit
) {
    when (state) {
        is UiState.Success -> {
            val promos = state.data
            if (promos.isEmpty()) return
            
            val pagerState = rememberPagerState { promos.size }
            val scope = rememberCoroutineScope()

            LaunchedEffect(pagerState) {
                while (true) {
                    delay(3000)
                    if (!pagerState.isScrollInProgress) {
                        val nextPage = (pagerState.currentPage + 1) % promos.size
                        pagerState.animateScrollToPage(nextPage)
                    }
                }
            }

            Card(
                modifier = modifier,
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxSize()
                    ) { page ->
                        AsyncImage(
                            model = promos[page].image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(R.drawable.promo_ads),
                            error = painterResource(R.drawable.promo_ads),
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { onClick(promos[page].id) }
                        )
                    }

                    IconButton(
                        onClick = {
                            scope.launch {
                                val prev = if (pagerState.currentPage == 0)
                                    promos.lastIndex
                                else
                                    pagerState.currentPage - 1
                                pagerState.animateScrollToPage(prev)
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 12.dp)
                            .size(36.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                                shape = RoundedCornerShape(18.dp)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    IconButton(
                        onClick = {
                            scope.launch {
                                val next = (pagerState.currentPage + 1) % promos.size
                                pagerState.animateScrollToPage(next)
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 12.dp)
                            .size(36.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                                shape = RoundedCornerShape(18.dp)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    PromosIndicator(
                        pageCount = promos.size,
                        currentPage = pagerState.currentPage,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 16.dp)
                    )
                }
            }
        }
        is UiState.Loading -> {

        }
        is UiState.Error -> {

        }
        else -> {}
    }
}
