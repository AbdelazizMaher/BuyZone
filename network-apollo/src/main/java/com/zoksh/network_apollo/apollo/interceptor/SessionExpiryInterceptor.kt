package com.zoksh.network_apollo.apollo.interceptor

import com.apollographql.apollo.api.http.HttpRequest
import com.apollographql.apollo.api.http.HttpResponse
import com.apollographql.apollo.network.http.HttpInterceptor
import com.apollographql.apollo.network.http.HttpInterceptorChain
import com.zoksh.core_session.session.store.SessionStore

class SessionExpiryInterceptor(
    private val sessionStore: SessionStore
): HttpInterceptor {
    override suspend fun intercept(
        request: HttpRequest,
        chain: HttpInterceptorChain
    ): HttpResponse {
        val session = sessionStore.session.value

        if (!session.isGuest && session.expiresIn != null) {
            if (session.expiresIn!! <= System.currentTimeMillis()) {
                sessionStore.clear(expired = true)
                return HttpResponse.Builder(401).build()
            }
        }
        return chain.proceed(request)
    }
}