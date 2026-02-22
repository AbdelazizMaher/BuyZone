package com.zoksh.feature_search.presentation.contract

import androidx.compose.runtime.Immutable
import com.zoksh.core_common.presentation.model.ProductUiModel
import com.zoksh.feature_search.presentation.components.filter.FilterUiState

sealed interface SearchContract {

    @Immutable
    data class State(
        val query: String = "",
        val isFilterVisible: Boolean = false,
        val products: List<ProductUiModel> = emptyList(),
        val filterState: FilterUiState = FilterUiState()
    )

    sealed interface Intent {
        data class OnQueryChange(val query: String) : Intent
        data object ToggleFilter : Intent
        data class OnProductClick(val productId: String) : Intent
        data object NavigateBack : Intent
        
        data class SelectCategory(val category: String) : Intent
        data class SelectBrand(val brand: String) : Intent
        data class OnPriceChange(val range: ClosedFloatingPointRange<Float>) : Intent
        data class SelectColor(val colorId: String) : Intent
        data class SelectSize(val size: String) : Intent
        data object ClearFilters : Intent
        data class RemoveFilter(val filter: String) : Intent
    }

    sealed interface Effect {
        data class NavigateToDetails(val productId: String) : Effect
        data object NavigateBack : Effect
    }
}
