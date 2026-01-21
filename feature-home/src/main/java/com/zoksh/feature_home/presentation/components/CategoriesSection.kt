package com.zoksh.feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zoksh.feature_home.presentation.model.CategoryUiModel

@Composable
fun CategoriesSection(
    modifier: Modifier = Modifier,
    categories: List<CategoryUiModel>,
    onCategoryClick: (String) -> Unit
) {
    Column(modifier = modifier) {
        SectionHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            title = "Categories"
        )
        BoxWithConstraints {
            val horizontalPadding = 8.dp * 2
            val spacing = 8.dp * 2
            val itemWidth = (maxWidth - horizontalPadding - spacing) / 3
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(categories, key = { it.title }) {
                    CategoryCard(
                        modifier = Modifier
                            .width(itemWidth)
                            .aspectRatio(1f),
                        category = it,
                        onCategoryClick = onCategoryClick
                    )
                }
            }
        }
    }
}