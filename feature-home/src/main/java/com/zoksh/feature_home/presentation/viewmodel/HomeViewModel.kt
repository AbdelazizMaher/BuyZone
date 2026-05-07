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
                    PromosUiModel(id = "1", image = "https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&fit=crop&q=80&w=800"),
                    PromosUiModel(id = "2", image = "https://images.unsplash.com/photo-1595950653106-6c9ebd614d3a?auto=format&fit=crop&q=80&w=800"),
                    PromosUiModel(id = "3", image = "https://images.unsplash.com/photo-1605348532760-6753d2c43329?auto=format&fit=crop&q=80&w=800")
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
                    BrandsUiModel(id = "1", "https://logodownload.org/wp-content/uploads/2014/04/nike-logo-0.png", "Nike"),
                    BrandsUiModel(id = "2", "https://logodownload.org/wp-content/uploads/2014/07/adidas-logo-0.png", "Adidas"),
                    BrandsUiModel(id = "3", "https://logodownload.org/wp-content/uploads/2014/07/puma-logo-1.png", "Puma"),
                    BrandsUiModel(id = "4", "https://logodownload.org/wp-content/uploads/2014/05/zara-logo-1.png", "Zara"),
                    BrandsUiModel(id = "5", "https://logodownload.org/wp-content/uploads/2014/04/h-m-logo.png", "H&M"),
                    BrandsUiModel(id = "6", "https://logodownload.org/wp-content/uploads/2017/05/rolex-logo.png", "Rolex")
                )
            ),
            trending = UiState.Success(
                listOf(
                    ProductUiModel(
                        "1",
                        "https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&fit=crop&q=80&w=400",
                        "Nike Air Max 270",
                        "$120.00",
                        "$150.00",
                        5,
                        false
                    ),
                    ProductUiModel(
                        "2",
                        "https://images.unsplash.com/photo-1620799140408-edc6dcb6d633?auto=format&fit=crop&q=80&w=400",
                        "Premium White Tee",
                        "$35.00",
                        "$45.00",
                        4,
                        true
                    ),
                    ProductUiModel(
                        "3",
                        "https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&q=80&w=400",
                        "Premium Silver Watch",
                        "$199.00",
                        "$250.00",
                        5,
                        false
                    ),
                    ProductUiModel(
                        "4",
                        "https://images.unsplash.com/photo-1608231387042-66d1773070a5?auto=format&fit=crop&q=80&w=400",
                        "Puma RS-X Bold",
                        "$110.00",
                        "$130.00",
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
