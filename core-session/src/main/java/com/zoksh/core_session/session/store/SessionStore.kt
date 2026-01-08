package com.zoksh.core_session.session.store

import com.zoksh.core_session.session.event.SessionEvent
import com.zoksh.core_session.session.model.Session
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface SessionStore {
    val session: StateFlow<Session>
    val event: SharedFlow<SessionEvent>

    fun update(session: Session)
    fun clear(expired: Boolean = false)
}