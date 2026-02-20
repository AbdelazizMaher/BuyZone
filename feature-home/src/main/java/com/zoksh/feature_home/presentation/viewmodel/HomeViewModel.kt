package com.zoksh.feature_home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.zoksh.feature_home.presentation.contract.HomeContract
import com.zoksh.feature_home.presentation.preview.HomePreviewData
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class HomeViewModel(

) : ViewModel() {

    private val _state = MutableStateFlow(HomePreviewData.state)
    val state = _state.asStateFlow()

    private val _effect = Channel<HomeContract.Effect>()
    val effect = _effect.receiveAsFlow()

    fun handleIntent(intent: HomeContract.Intent) {
        when (intent) {
            HomeContract.Intent.LoadData -> handleLoadData()
            is HomeContract.Intent.OnAddToFavClick -> handleOnAddToFavClick(intent.productId)
            is HomeContract.Intent.OnBrandClick -> handleOnBrandClick(intent.brandId)
            HomeContract.Intent.OnBrandsViewAllClick -> handleOnBrandsViewAllClick()
            is HomeContract.Intent.OnCategoryClick -> handleOnCategoryClick(intent.categoryId)
            HomeContract.Intent.OnNotificationClick -> handleOnNotificationClick()
            is HomeContract.Intent.OnProductClick -> handleOnProductClick(intent.productId)
            is HomeContract.Intent.OnPromoClick -> handleOnPromoClick(intent.promoId)
            HomeContract.Intent.OnSearchClick -> handleOnSearchClick()
            HomeContract.Intent.OnTrendingViewAllClick -> handleOnTrendingViewAllClick()
            HomeContract.Intent.OnRefresh -> {}
        }
    }

    private fun handleLoadData() {

    }

    private fun handleOnNotificationClick() {

    }

    private fun handleOnSearchClick() {

    }

    private fun handleOnPromoClick(promoId: String) {

    }

    private fun handleOnCategoryClick(categoryId: String) {

    }

    private fun handleOnBrandClick(brandId: String) {

    }

    private fun handleOnProductClick(productId: String) {

    }

    private fun handleOnBrandsViewAllClick() {

    }

    private fun handleOnTrendingViewAllClick() {

    }

    private fun handleOnAddToFavClick(productId: String) {

    }
}
