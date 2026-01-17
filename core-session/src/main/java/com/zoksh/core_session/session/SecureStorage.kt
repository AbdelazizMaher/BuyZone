package com.zoksh.core_session.session

import com.zoksh.core_session.session.model.Session

internal interface SecureStorage {
    fun writeSession(session: Session)
    fun readSession(): Session?
    fun clear()
}