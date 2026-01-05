package com.zoksh.feature_authentication.presentation.validation

import com.zoksh.feature_authentication.domain.validation.ValidationHandler

object SignUpWithEmailValidationChain {
    fun build(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): ValidationHandler {
        val head = NameValidationChain.build(name).apply {
            setNext(EmailValidationChain.build(email))
                .setNext(PasswordValidationChain.build(password))
                .setNext(
                    ConfirmPasswordValidationChain.build(password, confirmPassword)
                )
        }
        return head
    }
}