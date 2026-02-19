package com.zoksh.feature_search.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zoksh.core_ui.components.AppHeader
import com.zoksh.feature_search.presentation.components.HeaderSection

@Composable
fun SearchScreen(
    innerPadding: PaddingValues
) {
    LazyColumn(
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        item {
            AppHeader(
                modifier = Modifier.fillMaxWidth(),
                title = "Search Products",
                onBackClick = {}
            )
        }

        item {
            HeaderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                value = "",
                onValueChange = {},
                hint = "Search products...",
                onClearQuery = {},
                onFilterClick = {}
            )
        }
    }
}


