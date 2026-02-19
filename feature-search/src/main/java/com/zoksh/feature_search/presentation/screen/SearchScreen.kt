package com.zoksh.feature_search.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zoksh.feature_search.presentation.components.HeaderSection

@Composable
fun SearchScreen(
    innerPadding: PaddingValues
) {
    LazyColumn(
        contentPadding = innerPadding,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        item {
            HeaderSection(
                value = "",
                onValueChange = {},
                hint = "Search products...",
                onClearQuery = {},
                onBackClick = {},
                onFilterClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


