package com.zoksh.feature_details.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoksh.feature_details.presentation.contract.DetailsContract
import com.zoksh.feature_details.presentation.model.DetailsUiModel
import com.zoksh.core_common.presentation.model.ColorOption
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val _state = MutableStateFlow(DetailsContract.State())
    val state = _state.asStateFlow()

    private val _effect = Channel<DetailsContract.Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        _state.update {
            it.copy(
                product = DetailsUiModel(
                    id = "1",
                    name = "Nike Air Max 270",
                    category = "Shoes",
                    description = "The Nike Air Max 270 is Nike's first lifestyle Air Max, bringing you style, comfort and a big attitude. The design draws inspiration from Air Max icons, showcasing Nike's greatest innovation with its large window and fresh array of colors.",
                    price = "$150.00",
                    rating = 4.8,
                    images = listOf(
                        "https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&fit=crop&q=80&w=800",
                        "https://images.unsplash.com/photo-1543163521-1bf539c55dd2?auto=format&fit=crop&q=80&w=800",
                        "https://images.unsplash.com/photo-1549298916-b41d501d3772?auto=format&fit=crop&q=80&w=800"
                    ),
                    sizes = listOf("40", "41", "42", "43", "44", "45"),
                    colors = listOf(
                        ColorOption("1", "Red", Color(0xFFFF1744)),
                        ColorOption("2", "Blue", Color(0xFF2979FF)),
                        ColorOption("3", "Yellow", Color(0xFFFFEA00))
                    ),
                    selectedSize = "42",
                    selectedColorId = "1"
                )
            )
        }
    }

    fun handleIntent(intent: DetailsContract.Intent) {
        when (intent) {
            is DetailsContract.Intent.LoadProduct -> {  }
            is DetailsContract.Intent.SelectSize -> {
                _state.update { it.copy(product = it.product?.copy(selectedSize = intent.size)) }
            }
            is DetailsContract.Intent.SelectColor -> {
                _state.update { it.copy(product = it.product?.copy(selectedColorId = intent.colorId)) }
            }
            DetailsContract.Intent.AddToCart -> {
                viewModelScope.launch { _effect.send(DetailsContract.Effect.ProductAddedToCart) }
            }
            DetailsContract.Intent.NavigateBack -> {
                viewModelScope.launch { _effect.send(DetailsContract.Effect.NavigateBack) }
            }
        }
    }
}
