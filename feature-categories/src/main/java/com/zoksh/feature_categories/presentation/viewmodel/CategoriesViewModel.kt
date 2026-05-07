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
                    "https://images.unsplash.com/photo-1581655353564-df123a1eb820?auto=format&fit=crop&q=80&w=400",
                    "Classic White T-Shirt",
                    "$25.00",
                    "$35.00",
                    4,
                    true
                ),
                ProductUiModel(
                    "3",
                    "https://images.unsplash.com/photo-1608231387042-66d1773070a5?auto=format&fit=crop&q=80&w=400",
                    "Puma RS-X Bold",
                    "$110.00",
                    "$130.00",
                    5,
                    true
                ),
                ProductUiModel(
                    "4",
                    "https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&q=80&w=400",
                    "Premium Silver Watch",
                    "$199.00",
                    "$250.00",
                    5,
                    false
                ),
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
