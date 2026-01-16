package com.zoksh.feature_home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.zoksh.feature_home.presentation.contract.HomeContract
import com.zoksh.feature_home.presentation.preview.HomePreviewData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(

): ViewModel() {

    private val _state = MutableStateFlow(HomePreviewData.state)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeContract.Effect>()
    val effect = _effect.asSharedFlow()

    fun handleIntent(intent: HomeContract.Intent) {

    }
}