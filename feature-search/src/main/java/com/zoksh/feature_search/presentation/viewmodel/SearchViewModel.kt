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
                    ProductUiModel("1", "https://picsum.photos/300/300?random=1", "Adidas Ultraboost", "$150", "$200", 20, false),
                    ProductUiModel("2", "https://picsum.photos/300/300?random=2", "Nike Air Max", "$150", "$200", 25, true),
                    ProductUiModel("3", "https://picsum.photos/300/300?random=3", "Puma RS-X", "$110", "$150", 15, false),
                    ProductUiModel("4", "https://picsum.photos/300/300?random=4", "New Balance 574", "$90", "$120", 10, false),
                    ProductUiModel("5", "https://picsum.photos/300/300?random=5", "Reebok Classic", "$85", "$100", 5, true),
                    ProductUiModel("6", "https://picsum.photos/300/300?random=6", "Asics Gel-Kayano", "$160", "$200", 20, false)
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
