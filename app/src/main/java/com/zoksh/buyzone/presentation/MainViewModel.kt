package com.zoksh.buyzone.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoksh.buyzone.session.SessionAction
import com.zoksh.buyzone.session.SessionManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val sessionManager: SessionManager
) : ViewModel() {

    val bottomBarState = mutableStateOf(false)
    
    private val _uiEvent = MutableSharedFlow<MainUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        sessionManager.startObserving(viewModelScope)
        observeSessionEvents()
    }

    private fun observeSessionEvents() {
        viewModelScope.launch {
            sessionManager.navigationEvents.collect { action ->
                when (action) {
                    is SessionAction.Logout -> {
                        _uiEvent.emit(MainUiEvent.NavigateToLogin)
                    }
                }
            }
        }
    }

    fun setBottomBarVisible(isVisible: Boolean) {
        bottomBarState.value = isVisible
    }
}

sealed interface MainUiEvent {
    data object NavigateToLogin : MainUiEvent
}
