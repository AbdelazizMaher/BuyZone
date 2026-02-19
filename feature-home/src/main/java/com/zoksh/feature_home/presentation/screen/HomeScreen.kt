package com.zoksh.feature_home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zoksh.feature_home.presentation.components.BrandsSection
import com.zoksh.feature_home.presentation.components.CarouselPromosSection
import com.zoksh.feature_home.presentation.components.CategoriesSection
import com.zoksh.feature_home.presentation.components.HeaderSection
import com.zoksh.feature_home.presentation.components.SearchBarLauncher
import com.zoksh.feature_home.presentation.components.TrendingSection
import com.zoksh.feature_home.presentation.contract.HomeContract

@Composable
fun HomeScreen(
    state: HomeContract.State,
    onIntent: (HomeContract.Intent) -> Unit,
    innerPadding: PaddingValues
) {
    val bottomPadding = innerPadding.calculateBottomPadding()
    val topPadding = innerPadding.calculateTopPadding()

    LazyColumn(
        contentPadding = PaddingValues(bottom = bottomPadding),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item(key = "header", contentType = "header") {
            HeaderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(top = topPadding)
                    .padding(horizontal = 16.dp),
                header = state.header,
                onNotificationClick = {
                    onIntent(HomeContract.Intent.OnNotificationClick)
                }
            )
        }

        val itemModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)

        item(key = "search", contentType = "search") {
            SearchBarLauncher(
                modifier = itemModifier.padding(vertical = 12.dp),
                onClick = {
                    onIntent(HomeContract.Intent.OnSearchClick)
                }
            )
        }
        item(key = "promos", contentType = "section") {
            CarouselPromosSection(
                modifier = itemModifier.height(200.dp),
                promos = state.promos,
                onClick = { id ->
                    onIntent(HomeContract.Intent.OnPromoClick(id))
                }
            )
        }
        item(key = "categories", contentType = "section") {
            CategoriesSection(
                modifier = itemModifier,
                categories = state.categories,
                onCategoryClick = { id ->
                    onIntent(HomeContract.Intent.OnCategoryClick(id))
                }
            )
        }
        item(key = "brands", contentType = "section") {
            BrandsSection(
                modifier = itemModifier,
                brands = state.brands,
                onBrandClick = { id ->
                    onIntent(HomeContract.Intent.OnBrandClick(id))
                },
                onViewAllClick = {
                    onIntent(HomeContract.Intent.OnBrandsViewAllClick)
                }
            )
        }
        item(key = "trending", contentType = "section") {
            TrendingSection(
                modifier = itemModifier,
                trending = state.trending,
                onProductClick = { id ->
                    onIntent(HomeContract.Intent.OnProductClick(id))
                },
                onAddToFavClick = { id ->
                    onIntent(HomeContract.Intent.OnAddToFavClick(id))
                },
                onViewAllClick = {
                    onIntent(HomeContract.Intent.OnTrendingViewAllClick)
                }
            )
        }
    }
}
