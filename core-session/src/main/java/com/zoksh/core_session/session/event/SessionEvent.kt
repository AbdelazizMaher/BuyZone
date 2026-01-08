package com.zoksh.core_session.session.event

interface SessionEvent {
    data object Expired: SessionEvent
    data object LoggedOut: SessionEvent
}
