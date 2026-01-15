package com.zoksh.feature_home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            HeaderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = (-16).dp),
                header = state.header,
                onNotificationClick = {

                }
            )
        }
        item {
            CarouselPromosSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                promos = state.promos,
                onClick = {

                }
            )
        }
        item {
            CategoriesSection(
                categories = state.categories,
                onCategoryClick = {

                }
            )
        }
        item {
            BrandsSection(
                brands = state.brands,
                onBrandClick = {

                },
                onViewAllClick = {

                }
            )
        }
        item {
            TrendingSection(
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























