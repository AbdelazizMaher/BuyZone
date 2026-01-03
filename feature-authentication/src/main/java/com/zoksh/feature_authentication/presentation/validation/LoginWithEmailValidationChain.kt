package com.zoksh.feature_authentication.presentation.validation

import com.zoksh.feature_authentication.domain.validation.ValidationHandler
import com.zoksh.feature_authentication.domain.validation.rules.EmptyEmailHandler

object LoginWithEmailValidationChain {
    fun build(email: String, password: String): ValidationHandler {
        val head = EmailValidationChain.build(email).apply {
            setNext(PasswordValidationChain.build(password))
        }
        return head
    }
}