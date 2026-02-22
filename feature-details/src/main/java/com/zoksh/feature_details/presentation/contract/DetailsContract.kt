package com.zoksh.feature_details.presentation.contract

import androidx.compose.runtime.Immutable
import com.zoksh.feature_details.presentation.model.DetailsUiModel

sealed interface DetailsContract {

    @Immutable
    data class State(
        val isLoading: Boolean = false,
        val product: DetailsUiModel? = null,
        val error: String? = null
    )

    sealed interface Intent {
        data class LoadProduct(val productId: String) : Intent
        data class SelectSize(val size: String) : Intent
        data class SelectColor(val colorId: String) : Intent
        data object AddToCart : Intent
        data object NavigateBack : Intent
    }

    sealed interface Effect {
        data object NavigateBack : Effect
        data class ShowError(val message: String) : Effect
        data object ProductAddedToCart : Effect
    }
}
