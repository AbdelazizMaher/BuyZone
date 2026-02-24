package com.zoksh.feature_home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoksh.core_common.R
import com.zoksh.core_common.presentation.model.CategoryUiModel
import com.zoksh.core_common.presentation.model.ProductUiModel
import com.zoksh.core_common.presentation.ui_state.UiState
import com.zoksh.feature_home.presentation.contract.HomeContract
import com.zoksh.feature_home.presentation.model.BrandsUiModel
import com.zoksh.feature_home.presentation.model.HeaderUiModel
import com.zoksh.feature_home.presentation.model.PromosUiModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        HomeContract.State(
            header = HeaderUiModel(
                image = "https://i.pravatar.cc/150?img=3",
                message = "Good Morning 😊",
                name = "Abdelaziz",
                notificationCount = 3
            ),
            promos = UiState.Success(
                listOf(
                    PromosUiModel(id = "1", image = "https://picsum.photos/800/300?1"),
                    PromosUiModel(id = "2", image = "https://picsum.photos/800/300?2"),
                    PromosUiModel(id = "3", image = "https://picsum.photos/800/300?3")
                )
            ),
            categories = UiState.Success(
                listOf(
                    CategoryUiModel(id = "1", R.drawable.males_category, "Male", "👨"),
                    CategoryUiModel(id = "2", R.drawable.women_category, "Female", "👩"),
                    CategoryUiModel(id = "3", R.drawable.kids_category, "Kids", "👶")
                )
            ),
            brands = UiState.Success(
                listOf(
                    BrandsUiModel(id = "1", "https://logo.clearbit.com/apple.com", "Apple"),
                    BrandsUiModel(id = "2", "https://logo.clearbit.com/samsung.com", "Samsung"),
                    BrandsUiModel(id = "3", "https://logo.clearbit.com/sony.com", "Sony"),
                    BrandsUiModel(id = "4", "https://logo.clearbit.com/nike.com", "Nike"),
                    BrandsUiModel(id = "5", "https://logo.clearbit.com/adidas.com", "Adidas"),
                    BrandsUiModel(id = "6", "https://logo.clearbit.com/mi.com", "Xiaomi")
                )
            ),
            trending = UiState.Success(
                listOf(
                    ProductUiModel(
                        "1",
                        "https://picsum.photos/300/300?1",
                        "iPhone 15 Pro",
                        "$999",
                        "$1099",
                        5,
                        false
                    ),
                    ProductUiModel(
                        "2",
                        "https://picsum.photos/300/300?2",
                        "Galaxy S24",
                        "$899",
                        "$1099",
                        5,
                        true
                    ),
                    ProductUiModel(
                        "3",
                        "https://picsum.photos/300/300?1",
                        "iPhone 17 Pro",
                        "$999",
                        "$1099",
                        5,
                        false
                    ),
                    ProductUiModel(
                        "4",
                        "https://picsum.photos/300/300?2",
                        "Galaxy S20",
                        "$899",
                        "$1099",
                        5,
                        true
                    )
                )
            )
        )
    )
    val state = _state.asStateFlow()

    private val _effect = Channel<HomeContract.Effect>()
    val effect = _effect.receiveAsFlow()

    fun handleIntent(intent: HomeContract.Intent) {
        when (intent) {
            HomeContract.Intent.LoadData -> {}
            HomeContract.Intent.OnRefresh -> {}
            HomeContract.Intent.OnNotificationClick -> {}
            HomeContract.Intent.OnSearchClick -> {
                sendEffect(HomeContract.Effect.NavigateToSearch)
            }

            is HomeContract.Intent.OnPromoClick -> {

            }

            is HomeContract.Intent.OnCategoryClick -> {
                sendEffect(HomeContract.Effect.NavigateToCategory(intent.categoryId))
            }

            is HomeContract.Intent.OnBrandClick -> {
                sendEffect(HomeContract.Effect.NavigateToBrand(intent.brandId))
            }

            is HomeContract.Intent.OnProductClick -> {
                sendEffect(HomeContract.Effect.NavigateToProduct(intent.productId))
            }

            HomeContract.Intent.OnBrandsViewAllClick -> {
                sendEffect(HomeContract.Effect.NavigateToAllCategories)
            }

            HomeContract.Intent.OnTrendingViewAllClick -> {
                sendEffect(HomeContract.Effect.NavigateToAllTrending)
            }

            is HomeContract.Intent.OnAddToFavClick -> {

            }
        }
    }

    private fun sendEffect(effect: HomeContract.Effect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}
