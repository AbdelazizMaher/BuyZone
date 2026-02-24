package com.zoksh.feature_home.presentation.contract

import androidx.compose.runtime.Immutable
import com.zoksh.core_common.presentation.model.CategoryUiModel
import com.zoksh.core_common.presentation.ui_state.UiState
import com.zoksh.core_ui.snackbar.model.AppMessage
import com.zoksh.feature_home.presentation.model.BrandsUiModel
import com.zoksh.feature_home.presentation.model.HeaderUiModel
import com.zoksh.feature_home.presentation.model.PromosUiModel
import com.zoksh.core_common.presentation.model.ProductUiModel

sealed interface HomeContract {
    
    @Immutable
    data class State(
        val header: HeaderUiModel = HeaderUiModel(),
        val promos: UiState<List<PromosUiModel>> = UiState.Loading,
        val categories: UiState<List<CategoryUiModel>> = UiState.Loading,
        val brands: UiState<List<BrandsUiModel>> = UiState.Loading,
        val trending: UiState<List<ProductUiModel>> = UiState.Loading
    )

    sealed interface Intent {
        data object LoadData : Intent
        data object OnRefresh : Intent
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
