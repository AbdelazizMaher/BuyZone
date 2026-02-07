package com.zoksh.core_session.session.store

import com.zoksh.core_session.session.store.secure_storage.SecureStorage
import com.zoksh.core_session.session.event.SessionEvent
import com.zoksh.core_session.session.model.Session
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

internal class SecureSessionStore(
    private val secureStorage: SecureStorage
) : SessionStore {
    private val _session =
        MutableStateFlow(secureStorage.readSession() ?: guest())
    override val session: StateFlow<Session> = _session

    private val _event = MutableSharedFlow<SessionEvent>(
        replay = 0,
        extraBufferCapacity = 1
    )
    override val event: SharedFlow<SessionEvent> = _event

    override fun update(session: Session) {
        secureStorage.writeSession(session)
        _session.value = session
    }

    override fun clear(expired: Boolean) {
        secureStorage.clear()
        _session.value = guest()

        _event.tryEmit(
            if (expired) SessionEvent.Expired else SessionEvent.LoggedOut
        )
    }

    private fun guest() = Session(null, null, isGuest = true)
}