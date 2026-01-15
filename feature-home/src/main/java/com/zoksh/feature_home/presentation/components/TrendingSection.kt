package com.zoksh.feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zoksh.feature_home.presentation.model.TrendingUiModel

@Composable
fun TrendingSection(
    modifier: Modifier = Modifier,
    trending: List<TrendingUiModel>,
    onProductClick: (String) -> Unit,
    onAddToFavClick: (String) -> Unit,
    onViewAllClick: () -> Unit
) {
    Column(modifier = modifier) {
        SectionHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            title = "Trending Now",
            onViewAllClick = onViewAllClick
        )
        Spacer(Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(trending, key = { it.name }) {
                ProductCard(
                    product = it,
                    onClick = onProductClick,
                    onFavoriteClick = onAddToFavClick
                )
            }
        }
    }
}