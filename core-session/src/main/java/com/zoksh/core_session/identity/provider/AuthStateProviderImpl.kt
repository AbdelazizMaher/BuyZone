package com.zoksh.core_session.identity.provider

import com.zoksh.core_session.identity.model.AppAuthState
import com.zoksh.core_session.identity.store.UserStore
import com.zoksh.core_session.session.store.SessionStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class AuthStateProviderImpl(
    sessionStore: SessionStore,
    userStore: UserStore,
    scope: CoroutineScope
) : AuthStateProvider {

    override val authState: StateFlow<AppAuthState> = combine(
        sessionStore.session,
        userStore.user
    ) { session, user ->
        if (!session.isGuest && user != null) {
            AppAuthState.Authenticated(user)
        } else {
            AppAuthState.Guest
        }
    }.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AppAuthState.Guest
    )
}