package com.zoksh.feature_authentication.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class LoginViewModel(

): ViewModel() {

    private val _event = MutableSharedFlow<LoginContract.Effect>()
    val event = _event.asSharedFlow()
}