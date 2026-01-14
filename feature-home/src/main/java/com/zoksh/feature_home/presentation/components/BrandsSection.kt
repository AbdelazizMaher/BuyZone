package com.zoksh.feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zoksh.feature_home.presentation.model.BrandsUiModel

@Composable
fun BrandsSection(
    brands: List<BrandsUiModel>,
    onBrandClick: (String) -> Unit,
    onViewAllClick: () -> Unit
) {
    Column {
        SectionHeader(
            title = "Popular Brands",
            onViewAllClick = onViewAllClick
        )

        Spacer(Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.height(240.dp)
        ) {
            items(brands, key = { it.name }) {
                BrandCard(it, onBrandClick)
            }
        }
    }
}
