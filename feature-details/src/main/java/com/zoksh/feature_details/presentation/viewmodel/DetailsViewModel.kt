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
                    category = "Men shoes",
                    description = "The Nike Free Metcon 3 combines Nike Free flexibility around the forefoot with Metcon stability in the heel to help you get the most out of your training session.",
                    price = "$290.00",
                    rating = 4.8,
                    images = listOf(
                        "https://picsum.photos/800/800",
                        "https://picsum.photos/800/800",
                        "https://picsum.photos/800/800"
                    ),
                    sizes = listOf("6", "7", "8", "9", "10", "11"),
                    colors = listOf(
                        ColorOption("1", "Grey", Color(0xFF607D8B)),
                        ColorOption("2", "Red", Color(0xFFFF7043)),
                        ColorOption("3", "Yellow", Color(0xFFFFCA28))
                    ),
                    selectedSize = "8",
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
