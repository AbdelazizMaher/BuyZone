package com.zoksh.feature_home.presentation.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_common.R
import com.zoksh.core_common.presentation.component.CategoriesSection
import com.zoksh.core_common.presentation.model.CategoryUiModel
import com.zoksh.core_common.presentation.model.ProductUiModel
import com.zoksh.core_common.presentation.ui_state.UiState
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.feature_home.presentation.components.BrandsSection
import com.zoksh.feature_home.presentation.components.CarouselPromosSection
import com.zoksh.feature_home.presentation.components.HeaderSection
import com.zoksh.feature_home.presentation.components.SearchBarLauncher
import com.zoksh.feature_home.presentation.components.TrendingSection
import com.zoksh.feature_home.presentation.contract.HomeContract
import com.zoksh.feature_home.presentation.model.BrandsUiModel
import com.zoksh.feature_home.presentation.model.HeaderUiModel
import com.zoksh.feature_home.presentation.model.PromosUiModel

@Composable
fun HomeScreen(
    state: HomeContract.State,
    onIntent: (HomeContract.Intent) -> Unit,
    innerPadding: PaddingValues
) {
    val bottomPadding = innerPadding.calculateBottomPadding()
    val topPadding = innerPadding.calculateTopPadding()

    val itemModifier = remember {
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    }

    LazyColumn(
        contentPadding = PaddingValues(bottom = bottomPadding + 16.dp),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item(key = "header", contentType = "header") {
            HeaderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(top = topPadding)
                    .padding(horizontal = 16.dp),
                header = state.header,
                onNotificationClick = { onIntent(HomeContract.Intent.OnNotificationClick) },
                onSearchClick = { onIntent(HomeContract.Intent.OnSearchClick) }
            )
        }

        item(key = "search", contentType = "search") {
            SearchBarLauncher(
                modifier = itemModifier.padding(vertical = 8.dp),
                onClick = { onIntent(HomeContract.Intent.OnSearchClick) }
            )
        }

        item(key = "promos", contentType = "section") {
            CarouselPromosSection(
                modifier = itemModifier.height(200.dp),
                state = state.promos,
                onClick = { id -> onIntent(HomeContract.Intent.OnPromoClick(id)) }
            )
        }

        item(key = "categories", contentType = "section") {
            CategoriesSection(
                modifier = itemModifier,
                state = state.categories,
                onCategoryClick = { id -> onIntent(HomeContract.Intent.OnCategoryClick(id)) }
            )
        }

        item(key = "brands", contentType = "section") {
            BrandsSection(
                modifier = itemModifier,
                state = state.brands,
                onBrandClick = { id -> onIntent(HomeContract.Intent.OnBrandClick(id)) },
                onViewAllClick = { onIntent(HomeContract.Intent.OnBrandsViewAllClick) }
            )
        }

        item(key = "trending", contentType = "section") {
            TrendingSection(
                modifier = itemModifier,
                state = state.trending,
                onProductClick = { id -> onIntent(HomeContract.Intent.OnProductClick(id)) },
                onAddToFavClick = { id -> onIntent(HomeContract.Intent.OnAddToFavClick(id)) },
                onViewAllClick = { onIntent(HomeContract.Intent.OnTrendingViewAllClick) }
            )
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HomeScreenPreview() {
    val mockState = HomeContract.State(
        header = HeaderUiModel(
            image = "https://i.pravatar.cc/150?img=3",
            message = "Good Morning 😊",
            name = "Abdelaziz",
            notificationCount = 3
        ),
        promos = UiState.Success(listOf(
            PromosUiModel(id = "1", image = "https://picsum.photos/800/300?1"),
            PromosUiModel(id = "2", image = "https://picsum.photos/800/300?2"),
            PromosUiModel(id = "3", image = "https://picsum.photos/800/300?3")
        )),
        categories = UiState.Success(listOf(
            CategoryUiModel(id = "1", R.drawable.males_category, "Male", "👨"),
            CategoryUiModel(id = "2", R.drawable.women_category, "Female", "👩"),
            CategoryUiModel(id = "3", R.drawable.kids_category, "Kids", "👶")
        )),
        brands = UiState.Success(listOf(
            BrandsUiModel(id = "1", "https://logo.clearbit.com/apple.com", "Apple"),
            BrandsUiModel(id = "2", "https://logo.clearbit.com/samsung.com", "Samsung"),
            BrandsUiModel(id = "3", "https://logo.clearbit.com/sony.com", "Sony"),
            BrandsUiModel(id = "4", "https://logo.clearbit.com/nike.com", "Nike"),
            BrandsUiModel(id = "5", "https://logo.clearbit.com/adidas.com", "Adidas"),
            BrandsUiModel(id = "6", "https://logo.clearbit.com/mi.com", "Xiaomi")
        )),
        trending = UiState.Success(listOf(
            ProductUiModel("1", "https://picsum.photos/300/300?1", "iPhone 15 Pro", "$999", "$1099", 5, false),
            ProductUiModel("2", "https://picsum.photos/300/300?2", "Galaxy S24", "$899", "$1099", 5, true),
            ProductUiModel("3", "https://picsum.photos/300/300?1", "iPhone 17 Pro", "$999", "$1099", 5, false),
            ProductUiModel("4", "https://picsum.photos/300/300?2", "Galaxy S20", "$899", "$1099", 5, true)
        ))
    )

    BuyZoneTheme {
        HomeScreen(
            state = mockState,
            onIntent = {},
            innerPadding = PaddingValues(0.dp)
        )
    }
}
