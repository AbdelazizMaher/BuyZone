package com.zoksh.feature_authentication.presentation.validation

import com.zoksh.feature_authentication.domain.validation.ValidationHandler
import com.zoksh.feature_authentication.domain.validation.rules.PasswordNoMatchingHandler

object ConfirmPasswordValidationChain {
    fun build(password: String, confirmPassword: String): ValidationHandler {
        return PasswordNoMatchingHandler(password, confirmPassword)
    }
}