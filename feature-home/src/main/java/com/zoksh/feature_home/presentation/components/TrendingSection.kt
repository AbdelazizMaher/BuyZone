package com.zoksh.feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zoksh.core_common.presentation.ui_state.UiState
import com.zoksh.feature_home.presentation.model.TrendingUiModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TrendingSection(
    modifier: Modifier = Modifier,
    state: UiState<List<TrendingUiModel>>,
    onProductClick: (String) -> Unit,
    onAddToFavClick: (String) -> Unit,
    onViewAllClick: () -> Unit
) {
    when (state) {
        is UiState.Success -> {
            val displayItems = remember(state.data) { state.data.take(16) }
            Column(modifier = modifier) {
                SectionHeader(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    title = "Trending Now",
                    onViewAllClick = onViewAllClick
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    maxItemsInEachRow = 2
                ) {
                    displayItems.forEach { product ->
                        ProductCard(
                            modifier = Modifier.fillMaxWidth(0.48f),
                            product = product,
                            onClick = onProductClick,
                            onFavoriteClick = onAddToFavClick,
                        )
                    }
                }
            }
        }
        is UiState.Loading -> {
            
        }
        else -> {}
    }
}
