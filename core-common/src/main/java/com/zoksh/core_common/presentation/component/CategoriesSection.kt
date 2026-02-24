package com.zoksh.core_common.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zoksh.core_common.presentation.model.CategoryUiModel
import com.zoksh.core_common.presentation.ui_state.UiState


@Composable
fun CategoriesSection(
    modifier: Modifier = Modifier,
    state: UiState<List<CategoryUiModel>>,
    onCategoryClick: (String) -> Unit,
    title: String = "Categories",
    showViewAll: Boolean = false,
    onViewAllClick: () -> Unit = {}
) {
    when (state) {
        is UiState.Success -> {
            val categories = state.data
            Column(modifier = modifier) {
                SectionHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    title = title,
                    onViewAllClick = if (showViewAll) onViewAllClick else null
                )
                BoxWithConstraints {
                    val horizontalPadding = 8.dp * 2
                    val spacing = 8.dp * 2
                    val itemWidth = (maxWidth - horizontalPadding - spacing) / 3
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        items(categories, key = { it.id }) { category ->
                            CategoryCard(
                                modifier = Modifier
                                    .width(itemWidth)
                                    .aspectRatio(1f),
                                category = category,
                                onCategoryClick = onCategoryClick
                            )
                        }
                    }
                }
            }
        }
        is UiState.Loading -> {

        }
        else -> {}
    }
}
