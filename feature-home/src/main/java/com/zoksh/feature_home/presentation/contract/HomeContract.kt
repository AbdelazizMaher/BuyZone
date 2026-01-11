package com.zoksh.feature_home.presentation.contract

import com.zoksh.feature_home.presentation.model.BrandsUiModel
import com.zoksh.feature_home.presentation.model.CategoryUiModel
import com.zoksh.feature_home.presentation.model.HeaderUiModel
import com.zoksh.feature_home.presentation.model.PromosUiModel
import com.zoksh.feature_home.presentation.model.TrendingUiModel

sealed interface HomeContract {
    data class State(
        val header: HeaderUiModel,
        val promos: List<PromosUiModel> = emptyList(),
        val categories: List<CategoryUiModel> = emptyList(),
        val brands: List<BrandsUiModel> = emptyList(),
        val trending: List<TrendingUiModel> = emptyList()
    )

    sealed interface Intent {

    }

    sealed interface Effect {

    }
}
