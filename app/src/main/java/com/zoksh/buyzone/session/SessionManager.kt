package com.zoksh.buyzone.session

import com.zoksh.core_session.session.event.SessionEvent
import com.zoksh.core_session.session.store.SessionStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow


class SessionManager(
    private val sessionStore: SessionStore
) {
    private val _navigationEvents = Channel<SessionAction>()
    val navigationEvents = _navigationEvents.receiveAsFlow()

    fun startObserving(scope: CoroutineScope) {
        sessionStore.event
            .onEach { event ->
                when (event) {
                    is SessionEvent.Expired -> _navigationEvents.send(SessionAction.Logout(isExpired = true))
                    is SessionEvent.LoggedOut -> _navigationEvents.send(SessionAction.Logout(isExpired = false))
                }
            }
            .launchIn(scope)
    }
}

sealed interface SessionAction {
    data class Logout(val isExpired: Boolean) : SessionAction
}
