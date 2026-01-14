package com.zoksh.feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zoksh.feature_home.presentation.model.CategoryUiModel

@Composable
fun CategoriesSection(
    categories: List<CategoryUiModel>,
    onCategoryClick: (String) -> Unit
) {
    Column {
        SectionHeader(title = "Categories")
        Spacer(Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(categories, key = { it.title }) {
                CategoryCard(
                    category = it,
                    onCategoryClick = onCategoryClick
                )
            }
        }
    }
}