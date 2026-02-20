package com.zoksh.core_common.presentation.ui_state

import androidx.compose.runtime.Immutable


@Immutable
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<out T>(val data: T) : UiState<T>
    data class Error(val message: String? = null) : UiState<Nothing>
    data object Empty : UiState<Nothing>
}
