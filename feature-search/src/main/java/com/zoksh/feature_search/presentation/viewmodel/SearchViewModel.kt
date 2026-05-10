package com.zoksh.feature_search.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoksh.core_common.presentation.model.ColorOption
import com.zoksh.core_common.presentation.model.ProductUiModel
import com.zoksh.feature_search.presentation.components.filter.FilterUiState
import com.zoksh.feature_search.presentation.contract.SearchContract
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _state = MutableStateFlow(SearchContract.State())
    val state: StateFlow<SearchContract.State> = _state.asStateFlow()

    private val _effect = Channel<SearchContract.Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        _state.update {
            it.copy(
                products = listOf(
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
                    ProductUiModel(
                        "5",
                        "https://images.unsplash.com/photo-1556821840-3a63f95609a7?auto=format&fit=crop&q=80&w=400",
                        "Classic Black Hoodie",
                        "$55.00",
                        "$75.00",
                        5,
                        false
                    ),
                    ProductUiModel(
                        "6",
                        "https://images.unsplash.com/photo-1548036328-c9fa89d128fa?auto=format&fit=crop&q=80&w=400",
                        "Leather Backpack",
                        "$85.00",
                        "$110.00",
                        4,
                        false
                    )
                ),
                filterState = FilterUiState(
                    categories = listOf("Electronics", "Fashion", "Home & Living", "Sports", "Beauty", "Books"),
                    brands = listOf("Samsung", "Apple", "Nike", "Adidas", "Sony", "LG", "Zara", "H&M"),
                    colors = listOf(
                        ColorOption("1", "Black", Color.Black),
                        ColorOption("2", "White", Color.White),
                        ColorOption("3", "Blue", Color.Blue),
                        ColorOption("4", "Red", Color.Red),
                        ColorOption("5", "Yellow", Color.Yellow)
                    ),
                    sizes = listOf("XS", "S", "M", "L", "XL", "XXL")
                )
            )
        }
    }

    fun handleIntent(intent: SearchContract.Intent) {
        when (intent) {
            is SearchContract.Intent.OnQueryChange -> {
                _state.update { it.copy(query = intent.query) }
            }
            SearchContract.Intent.ToggleFilter -> {
                _state.update { it.copy(isFilterVisible = !it.isFilterVisible) }
            }
            is SearchContract.Intent.OnProductClick -> {
                viewModelScope.launch {
                    _effect.send(SearchContract.Effect.NavigateToDetails(intent.productId))
                }
            }
            SearchContract.Intent.NavigateBack -> {
                viewModelScope.launch {
                    _effect.send(SearchContract.Effect.NavigateBack)
                }
            }
            is SearchContract.Intent.SelectCategory -> {
                val current = _state.value.filterState.selectedCategories
                val next = if (current.contains(intent.category)) current - intent.category else current + intent.category
                updateFilter { it.copy(selectedCategories = next) }
            }
            is SearchContract.Intent.SelectBrand -> {
                val current = _state.value.filterState.selectedBrands
                val next = if (current.contains(intent.brand)) current - intent.brand else current + intent.brand
                updateFilter { it.copy(selectedBrands = next) }
            }
            is SearchContract.Intent.OnPriceChange -> {
                updateFilter { it.copy(priceRange = intent.range) }
            }
            is SearchContract.Intent.SelectColor -> {
                updateFilter { it.copy(selectedColorId = intent.colorId) }
            }
            is SearchContract.Intent.SelectSize -> {
                updateFilter { it.copy(selectedSize = intent.size) }
            }
            SearchContract.Intent.ClearFilters -> {
                updateFilter { FilterUiState() }
            }
            is SearchContract.Intent.RemoveFilter -> {
                val currentFilter = _state.value.filterState
                val next = when {
                    currentFilter.selectedCategories.contains(intent.filter) -> 
                        currentFilter.copy(selectedCategories = currentFilter.selectedCategories - intent.filter)
                    currentFilter.selectedBrands.contains(intent.filter) -> 
                        currentFilter.copy(selectedBrands = currentFilter.selectedBrands - intent.filter)
                    currentFilter.selectedColorId == intent.filter -> 
                        currentFilter.copy(selectedColorId = null)
                    currentFilter.selectedSize == intent.filter -> 
                        currentFilter.copy(selectedSize = null)
                    else -> currentFilter
                }
                updateFilter { next }
            }
        }
    }

    private fun updateFilter(transform: (FilterUiState) -> FilterUiState) {
        _state.update {
            val nextFilter = transform(it.filterState)
            it.copy(
                filterState = nextFilter.copy(
                    activeFiltersCount = nextFilter.selectedCategories.size + 
                                         nextFilter.selectedBrands.size + 
                                         (if (nextFilter.selectedColorId != null) 1 else 0) + 
                                         (if (nextFilter.selectedSize != null) 1 else 0)
                )
            )
        }
    }
}
