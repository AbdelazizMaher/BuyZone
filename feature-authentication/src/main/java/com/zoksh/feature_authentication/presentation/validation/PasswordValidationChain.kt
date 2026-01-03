package com.zoksh.feature_authentication.presentation.validation

import com.zoksh.feature_authentication.domain.validation.ValidationHandler
import com.zoksh.feature_authentication.domain.validation.rules.EmptyPasswordHandler
import com.zoksh.feature_authentication.domain.validation.rules.PasswordNoLowerCaseHandler
import com.zoksh.feature_authentication.domain.validation.rules.PasswordNoNumberHandler
import com.zoksh.feature_authentication.domain.validation.rules.PasswordNoSpecialCharHandler
import com.zoksh.feature_authentication.domain.validation.rules.PasswordNoUpperCaseHandler
import com.zoksh.feature_authentication.domain.validation.rules.PasswordTooShortHandler

object PasswordValidationChain {
    fun build(password: String): ValidationHandler {
        val head = EmptyPasswordHandler(password).apply {
            setNext(PasswordTooShortHandler(password))
                .setNext(PasswordNoUpperCaseHandler(password))
                .setNext(PasswordNoLowerCaseHandler(password))
                .setNext(PasswordNoNumberHandler(password))
                .setNext(PasswordNoSpecialCharHandler(password))
        }
        return head
    }
}