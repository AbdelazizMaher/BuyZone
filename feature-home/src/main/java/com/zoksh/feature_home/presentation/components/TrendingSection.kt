package com.zoksh.feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
                .padding(8.dp),
            title = "Trending Now",
            onViewAllClick = onViewAllClick
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            trending
                .chunked(2)
                .take(8)
                .forEach { rowItems ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rowItems.forEach { product ->
                            ProductCard(
                                modifier = Modifier.weight(1f),
                                product = product,
                                onClick = onProductClick,
                                onFavoriteClick = onAddToFavClick
                            )
                        }
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
        }
    }
}