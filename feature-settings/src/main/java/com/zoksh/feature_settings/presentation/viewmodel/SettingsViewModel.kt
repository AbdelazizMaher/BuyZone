package com.zoksh.feature_settings.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoksh.core_common.presentation.ui_state.UiState
import com.zoksh.feature_settings.presentation.contract.SettingsContract
import com.zoksh.feature_settings.presentation.contract.UserUiModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    private val _state = MutableStateFlow(SettingsContract.State())
    val state = _state.asStateFlow()

    private val _effect = Channel<SettingsContract.Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        handleIntent(SettingsContract.Intent.LoadProfile)
    }

    fun handleIntent(intent: SettingsContract.Intent) {
        when (intent) {
            SettingsContract.Intent.LoadProfile -> loadProfile()
            SettingsContract.Intent.Orders -> if (!_state.value.isGuest) sendEffect(SettingsContract.Effect.NavigateToOrders)
            SettingsContract.Intent.Addresses -> if (!_state.value.isGuest) sendEffect(SettingsContract.Effect.NavigateToAddresses)
            SettingsContract.Intent.Wishlist -> sendEffect(SettingsContract.Effect.NavigateToWishlist)
            SettingsContract.Intent.CurrencySelection -> {
            }

            SettingsContract.Intent.Notifications -> {
            }

            SettingsContract.Intent.AppTheme -> {
                val nextTheme = if (_state.value.appTheme == "Light") "Dark" else "Light"
                _state.update { it.copy(appTheme = nextTheme) }
            }

            SettingsContract.Intent.AboutUs -> {
            }

            SettingsContract.Intent.HelpContact -> {
            }

            SettingsContract.Intent.Logout -> {
                _state.update { 
                    it.copy(
                        isGuest = true, 
                        profileState = UiState.Success(UserUiModel("Guest", "")) 
                    ) 
                }
            }
            SettingsContract.Intent.LoginRegister -> sendEffect(SettingsContract.Effect.NavigateToLogin)
        }
    }

    private fun loadProfile() {
        _state.update {
            it.copy(
                isGuest = true,
                profileState = UiState.Success(UserUiModel("Guest", ""))
            )
        }
    }

    private fun sendEffect(effect: SettingsContract.Effect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}
