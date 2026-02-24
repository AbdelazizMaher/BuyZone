package com.zoksh.feature_home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zoksh.core_common.presentation.component.SectionHeader
import com.zoksh.core_common.presentation.ui_state.UiState
import com.zoksh.feature_home.presentation.model.BrandsUiModel

@Composable
fun BrandsSection(
    modifier: Modifier = Modifier,
    state: UiState<List<BrandsUiModel>>,
    onBrandClick: (String) -> Unit,
    onViewAllClick: () -> Unit
) {
    when (state) {
        is UiState.Success -> {
            Column(modifier = modifier) {
                SectionHeader(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    title = "Popular Brands",
                    onViewAllClick = onViewAllClick
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    maxItemsInEachRow = 3
                ) {
                    state.data.take(9).forEach { brand ->
                        BrandCard(
                            brand = brand,
                            onClick = onBrandClick,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
        is UiState.Loading -> {  }
        else -> {}
    }
}