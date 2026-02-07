package com.zoksh.core_session.identity.contract

import com.zoksh.core_session.identity.model.AppAuthState
import kotlinx.coroutines.flow.StateFlow

interface AuthStateProvider {
    val authState: StateFlow<AppAuthState>
}