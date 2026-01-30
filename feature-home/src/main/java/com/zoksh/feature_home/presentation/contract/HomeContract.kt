package com.zoksh.feature_home.presentation.contract

import com.zoksh.core_ui.snackbar.model.AppMessage
import com.zoksh.feature_home.presentation.model.BrandsUiModel
import com.zoksh.feature_home.presentation.model.CategoryUiModel
import com.zoksh.feature_home.presentation.model.HeaderUiModel
import com.zoksh.feature_home.presentation.model.PromosUiModel
import com.zoksh.feature_home.presentation.model.TrendingUiModel

sealed interface HomeContract {
    data class State(
        val header: HeaderUiModel = HeaderUiModel(),
        val promos: List<PromosUiModel> = emptyList(),
        val categories: List<CategoryUiModel> = emptyList(),
        val brands: List<BrandsUiModel> = emptyList(),
        val trending: List<TrendingUiModel> = emptyList()
    )

    sealed interface Intent {
        data object LoadData : Intent
        data object OnNotificationClick : Intent
        data object OnSearchClick : Intent

        data class OnPromoClick(val promoId: String) : Intent
        data class OnCategoryClick(val categoryId: String) : Intent
        data class OnBrandClick(val brandId: String) : Intent
        data class OnProductClick(val productId: String) : Intent

        data object OnBrandsViewAllClick : Intent
        data object OnTrendingViewAllClick : Intent

        data class OnAddToFavClick(val productId: String) : Intent
    }

    sealed interface Effect {
        data object NavigateToSearch : Effect
        data class NavigateToBrand(val brandId: String) : Effect
        data class NavigateToCategory(val categoryId: String) : Effect
        data object NavigateToAllCategories : Effect
        data class NavigateToProduct(val productId: String) : Effect
        data object NavigateToAllTrending : Effect
        data class ShowMessage(val message: AppMessage): Effect
    }
}
