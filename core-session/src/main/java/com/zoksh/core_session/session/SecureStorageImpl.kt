package com.zoksh.core_session.session

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.zoksh.core_session.session.model.Session

class SecureStorageImpl(
    context: Context
) : SecureStorage {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "session",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun writeSession(session: Session) {
        prefs.edit()
            .putString(KEY_ACCESS_TOKEN, session.accessToken)
            .putLong(KEY_EXPIRES_AT, session.expiresIn ?: -1)
            .apply()
    }

    override fun readSession(): Session? {
        val token = prefs.getString(KEY_ACCESS_TOKEN, null) ?: return null
        val expiresIn = prefs.getLong(KEY_EXPIRES_AT, -1L)
        if (expiresIn <= 0L) return null

        return Session(
            accessToken = token,
            expiresIn = expiresIn,
            isGuest = false
        )
    }

    override fun clear() {
        prefs.edit().clear().apply()
    }

    private companion object {
        const val KEY_ACCESS_TOKEN = "access_token"
        const val KEY_EXPIRES_AT = "expires_at"
    }
}