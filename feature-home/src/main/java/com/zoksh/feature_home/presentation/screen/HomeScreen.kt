package com.zoksh.feature_home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zoksh.feature_home.presentation.components.AppSearchBar
import com.zoksh.feature_home.presentation.components.BrandsSection
import com.zoksh.feature_home.presentation.components.CarouselPromosSection
import com.zoksh.feature_home.presentation.components.CategoriesSection
import com.zoksh.feature_home.presentation.components.HeaderSection
import com.zoksh.feature_home.presentation.components.TrendingSection
import com.zoksh.feature_home.presentation.contract.HomeContract

@Composable
fun HomeScreen(
    state: HomeContract.State,
    onIntent: (HomeContract.Intent) -> Unit,
    innerPadding: PaddingValues
) {
    LazyColumn(
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            HeaderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface),
                header = state.header,
                onNotificationClick = {

                }
            )
        }
        item {
            Box(
                modifier = Modifier
                    .clickable{ }
            ) {
                AppSearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = "",
                    hint = "Search for products...",
                )
            }
        }
        item {
            CarouselPromosSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .height(160.dp),
                promos = state.promos,
                onClick = {

                }
            )
        }
        item {
            CategoriesSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                categories = state.categories,
                onCategoryClick = {

                }
            )
        }
        item {
            BrandsSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                brands = state.brands,
                onBrandClick = {

                },
                onViewAllClick = {

                }
            )
        }
        item {
            TrendingSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                trending = state.trending,
                onProductClick = {

                },
                onAddToFavClick = {

                },
                onViewAllClick = {

                }
            )
        }
    }
}























