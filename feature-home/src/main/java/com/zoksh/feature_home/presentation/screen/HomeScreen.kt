package com.zoksh.feature_home.presentation.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.zoksh.feature_home.presentation.contract.HomeContract
import com.zoksh.feature_home.presentation.model.BrandsUiModel
import com.zoksh.feature_home.presentation.model.CategoryUiModel
import com.zoksh.feature_home.presentation.model.HeaderUiModel
import com.zoksh.feature_home.presentation.model.PromosUiModel
import com.zoksh.feature_home.presentation.model.TrendingUiModel

@Composable
fun HomeScreen(
    state: HomeContract.State,
    onIntent: (HomeContract.Intent) -> Unit
) {
    LazyColumn(

    ) {
        item {
            HeaderSection(
                header = state.header,
                onNotificationClick = {

                }
            )
        }
        item {
            CarouselPromosSection(
                promos = state.promos,
                onClick = {

                }
            )
        }
        item {
            CategoriesSection(
                categories = state.categories,
                onClick = {

                }
            )
        }
        item {
            BrandsSection(
                brands = state.brands,
                onClick = {

                },
                onViewAllClick = {

                }
            )
        }
        item {
            TrendingSection(
                trending = state.trending,
                onClick = {

                },
                onAddToFavClick = {

                },
                onViewAllClick = {

                }
            )
        }

    }
}

@Composable
fun HeaderSection(
    header: HeaderUiModel,
    onNotificationClick: () -> Unit,
) {

}

@Composable
fun CarouselPromosSection(
    promos: List<PromosUiModel>,
    onClick: () -> Unit
) {

}

@Composable
fun CategoriesSection(
    categories: List<CategoryUiModel>,
    onClick: () -> Unit
) {

}

@Composable
fun BrandsSection(
    brands: List<BrandsUiModel>,
    onClick: () -> Unit,
    onViewAllClick: () -> Unit
) {

}

@Composable
fun TrendingSection(
    trending: List<TrendingUiModel>,
    onClick: () -> Unit,
    onAddToFavClick: () -> Unit,
    onViewAllClick: () -> Unit
) {

}
