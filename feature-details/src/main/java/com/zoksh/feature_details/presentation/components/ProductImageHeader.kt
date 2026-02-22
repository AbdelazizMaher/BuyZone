package com.zoksh.feature_details.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zoksh.core_common.R
import com.zoksh.core_ui.components.AppIndicator
import com.zoksh.core_ui.theme.BuyZoneTheme

@Composable
fun ProductImageHeader(
    images: List<String>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { images.size }

    Box(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) { page ->
            AsyncImage(
                model = images[page],
                contentDescription = null,
                contentScale = ContentScale.Fit,
                placeholder = painterResource(R.drawable.addidas_logo),
                error = painterResource(R.drawable.addidas_logo),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            )
        }
        AppIndicator(
            pageCount = images.size,
            currentPage = pagerState.currentPage,
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ProductImageHeaderPreview() {
    BuyZoneTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ProductImageHeader(
                images = listOf("https://picsum.photos/800/800", "https://picsum.photos/800/800")
            )
        }
    }
}
