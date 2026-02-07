package com.zoksh.core_session.identity.store.secure_storage

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.zoksh.core_session.identity.model.User
import com.zoksh.core_session.identity.model.UserState
import kotlinx.coroutines.flow.first

class UserStorageImpl(
    private val context: Context
) : UserStorage {

    private val Context.dataStore by preferencesDataStore(name = USER_DATA)

    override suspend fun save(user: User) {
        context.dataStore.edit {
            it[USER_NAME] = user.name
            it[USER_EMAIL] = user.email
            it[USER_ID] = user.id
            it[USER_IMAGE] = user.image
            it[IS_SHOP_CREATED] = user.isShopCreated
            it[CART_ID] = user.cartId
            it[USER_STATE] = user.state.name
        }
    }

    override suspend fun get(): User? {
        val preferences = context.dataStore.data.first()
        val id = preferences[USER_ID] ?: return null

        return User(
            name = preferences[USER_NAME] ?: "",
            email = preferences[USER_EMAIL] ?: "",
            id = id,
            image = preferences[USER_IMAGE] ?: "",
            isShopCreated = preferences[IS_SHOP_CREATED] ?: false,
            cartId = preferences[CART_ID] ?: "",
            state = preferences[USER_STATE]?.let {
                runCatching { UserState.valueOf(it) }.getOrDefault(UserState.UNAUTHENTICATED)
            } ?: UserState.UNAUTHENTICATED
        )
    }

    override suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }

    companion object {
        private const val USER_DATA = "user_data"
        private val USER_NAME = stringPreferencesKey("user_name")
        private val USER_EMAIL = stringPreferencesKey("user_email")
        private val USER_ID = stringPreferencesKey("user_id")
        private val USER_IMAGE = stringPreferencesKey("user_image")
        private val IS_SHOP_CREATED = booleanPreferencesKey("is_shop_created")
        private val CART_ID = stringPreferencesKey("cart_id")
        private val USER_STATE = stringPreferencesKey("user_state")
    }
}
