package com.zoksh.core_session.session.store

import com.zoksh.core_session.session.SecureStorage
import com.zoksh.core_session.session.event.SessionEvent
import com.zoksh.core_session.session.model.Session
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class SecureSessionStore(
    private val secureStorage: SecureStorage
) : SessionStore {
    override val session: StateFlow<Session>
        get() = MutableStateFlow(secureStorage.readSession() ?: guest())
    override val event: SharedFlow<SessionEvent>
        get() = MutableSharedFlow()

    override fun update(session: Session) {
        secureStorage.writeSession(session)
        (this.session as MutableStateFlow).value = session
    }

    override fun clear(expired: Boolean) {
        secureStorage.clear()
        (this.session as MutableStateFlow).value = guest()

        (this.event as MutableSharedFlow).tryEmit(
            if (expired) SessionEvent.Expired else SessionEvent.LoggedOut
        )
    }

    private fun guest() = Session(null, null, isGuest = true)
}