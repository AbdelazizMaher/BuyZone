package com.zoksh.feature_authentication.presentation.validation

import com.zoksh.feature_authentication.domain.validation.ValidationHandler
import com.zoksh.feature_authentication.domain.validation.rules.EmptyEmailHandler

object EmailValidationChain {
    fun build(email: String) : ValidationHandler {
        val head = EmptyEmailHandler(email).apply {
            setNext(EmptyEmailHandler(email))
        }
        return head
    }
}