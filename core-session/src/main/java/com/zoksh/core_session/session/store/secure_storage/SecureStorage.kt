package com.zoksh.core_session.session.store.secure_storage

import com.zoksh.core_session.session.model.Session

internal interface SecureStorage {
    fun writeSession(session: Session)
    fun readSession(): Session?
    fun clear()
}