package com.zoksh.feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zoksh.feature_home.presentation.model.BrandsUiModel

@Composable
fun BrandsSection(
    modifier: Modifier = Modifier,
    brands: List<BrandsUiModel>,
    onBrandClick: (String) -> Unit,
    onViewAllClick: () -> Unit
) {
    Column(modifier = modifier) {
        SectionHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            title = "Popular Brands",
            onViewAllClick = onViewAllClick
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 3
        ) {
            brands.take(9).forEach { brand ->
                BrandCard(
                    brand = brand,
                    onClick = onBrandClick,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
