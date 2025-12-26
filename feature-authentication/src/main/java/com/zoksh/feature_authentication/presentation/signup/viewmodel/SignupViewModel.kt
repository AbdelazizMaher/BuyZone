package com.zoksh.feature_authentication.presentation.signup.viewmodel

import androidx.lifecycle.ViewModel
import com.zoksh.feature_authentication.presentation.signup.contract.SignupContract
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SignupViewModel(

): ViewModel() {

    private val _event = MutableSharedFlow<SignupContract.Effect>()
    val event = _event.asSharedFlow()
}