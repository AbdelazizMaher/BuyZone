package com.zoksh.feature_home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zoksh.feature_home.R
import com.zoksh.feature_home.presentation.model.PromosUiModel

@Composable
fun CarouselPromosSection(
    promos: List<PromosUiModel>,
    onClick: () -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { promos.size }
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box{
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { }
            AsyncImage(
                model = promos,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.avatar),
                error = painterResource(R.drawable.avatar),
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick() }
            )
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