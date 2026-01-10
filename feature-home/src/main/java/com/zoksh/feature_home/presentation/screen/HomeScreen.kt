package com.zoksh.feature_home.presentation.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.zoksh.feature_home.presentation.contract.HomeContract

@Composable
fun HomeScreen(
    state: HomeContract.State,
    onIntent: (HomeContract.Intent) -> Unit
) {
    LazyColumn(

    ) {
        item {
            HeaderSection()
        }
        item {
            CarouselPromosSection()
        }
        item {
            CategoriesSection()
        }
        item {
            BrandsSection()
        }
        item {
            TrendingSection()
        }

    }
}

@Composable
fun HeaderSection(
    image: String?,
    message: String,
    name: String,
    onNotificationClick: () -> Unit,
) {

}

@Composable
fun CarouselPromosSection(
    promos: List<String>,
    onClick: () -> Unit
) {

}

@Composable
fun CategoriesSection(
    imageRes: Int,
    title: String,
    infoIcon: Int,
    onClick: () -> Unit
) {

}

@Composable
fun BrandsSection(
    logoImage: String,
    name: String,
    onClick: () -> Unit
) {

}

@Composable
fun TrendingSection(
    image: String,
    name: String,
    price: String,
    onClick: () -> Unit,
    onAddToFavClick: () -> Unit
) {

}
