package com.zoksh.feature_categories.presentation.contract

import androidx.compose.runtime.Immutable
import com.zoksh.core_common.presentation.model.CategoryUiModel
import com.zoksh.core_common.presentation.model.ProductUiModel
import com.zoksh.core_common.presentation.ui_state.UiState

sealed interface CategoriesContract {

    @Immutable
    data class State(
        val searchQuery: String = "",
        val categories: UiState<List<CategoryUiModel>> = UiState.Loading,
        val selectedCategoryId: String? = null,
        val productTypes: UiState<List<String>> = UiState.Loading,
        val selectedProductType: String? = null,
        val products: UiState<List<ProductUiModel>> = UiState.Loading
    )

    sealed interface Intent {
        data object LoadInitialData : Intent
        data class OnSearchQueryChange(val query: String) : Intent
        data class OnCategorySelect(val categoryId: String) : Intent
        data class OnProductTypeSelect(val type: String) : Intent
        data class OnProductClick(val productId: String) : Intent
        data object OnBackClick : Intent
    }

    sealed interface Effect {
        data object NavigateBack : Effect
        data class NavigateToDetails(val productId: String) : Effect
    }
}
