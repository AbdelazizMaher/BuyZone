package com.zoksh.feature_categories.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoksh.core_common.R
import com.zoksh.core_common.presentation.model.CategoryUiModel
import com.zoksh.core_common.presentation.model.ProductUiModel
import com.zoksh.core_common.presentation.ui_state.UiState
import com.zoksh.feature_categories.presentation.contract.CategoriesContract
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {

    private val _state = MutableStateFlow(CategoriesContract.State())
    val state = _state.asStateFlow()

    private val _effect = Channel<CategoriesContract.Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        handleIntent(CategoriesContract.Intent.LoadInitialData)
    }

    fun handleIntent(intent: CategoriesContract.Intent) {
        when (intent) {
            CategoriesContract.Intent.LoadInitialData -> loadData()
            is CategoriesContract.Intent.OnSearchQueryChange -> {
                _state.update { it.copy(searchQuery = intent.query) }
            }
            is CategoriesContract.Intent.OnCategorySelect -> {
                _state.update { it.copy(selectedCategoryId = intent.categoryId) }
            }
            is CategoriesContract.Intent.OnProductTypeSelect -> {
                _state.update { it.copy(selectedProductType = intent.type) }
            }
            is CategoriesContract.Intent.OnProductClick -> {
                viewModelScope.launch {
                    _effect.send(CategoriesContract.Effect.NavigateToDetails(intent.productId))
                }
            }
            CategoriesContract.Intent.OnBackClick -> {
                viewModelScope.launch {
                    _effect.send(CategoriesContract.Effect.NavigateBack)
                }
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            val mockCategories = listOf(
                CategoryUiModel(id = "1", R.drawable.males_category, "Male", "👨"),
                CategoryUiModel(id = "2", R.drawable.women_category, "Female", "👩"),
                CategoryUiModel(id = "3", R.drawable.kids_category, "Kids", "👶"),
            )
            val mockTypes = listOf("All", "Shoes", "Clothing", "Accessories", "Equipment")
            val mockProducts = listOf(
                ProductUiModel("1", "https://picsum.photos/300/300?random=1", "Adidas Ultraboost", "$150", "$200", 20, false),
                ProductUiModel("2", "https://picsum.photos/300/300?random=2", "Nike Air Max", "$150", "$200", 25, true),
                ProductUiModel("3", "https://picsum.photos/300/300?random=3", "Puma RS-X", "$110", "$150", 15, false),
                ProductUiModel("4", "https://picsum.photos/300/300?random=4", "New Balance 574", "$90", "$120", 10, false),
            )

            _state.update {
                it.copy(
                    categories = UiState.Success(mockCategories),
                    productTypes = UiState.Success(mockTypes),
                    products = UiState.Success(mockProducts),
                    selectedCategoryId = mockCategories.firstOrNull()?.id,
                    selectedProductType = mockTypes.first()
                )
            }
        }
    }
}
