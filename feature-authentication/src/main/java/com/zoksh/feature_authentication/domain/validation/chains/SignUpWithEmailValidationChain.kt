package com.zoksh.feature_authentication.domain.validation.chains

import com.zoksh.feature_authentication.domain.model.ValidationError
import com.zoksh.feature_authentication.domain.validation.ValidationHandler
import com.zoksh.feature_authentication.domain.validation.rules.EmptyEmailHandler
import com.zoksh.feature_authentication.domain.validation.rules.EmptyNameHandler
import com.zoksh.feature_authentication.domain.validation.rules.EmptyPasswordHandler
import com.zoksh.feature_authentication.domain.validation.rules.InvalidEmailHandler
import com.zoksh.feature_authentication.domain.validation.rules.InvalidNameHandler
import com.zoksh.feature_authentication.domain.validation.rules.PasswordNoLowerCaseHandler
import com.zoksh.feature_authentication.domain.validation.rules.PasswordNoMatchingHandler
import com.zoksh.feature_authentication.domain.validation.rules.PasswordNoNumberHandler
import com.zoksh.feature_authentication.domain.validation.rules.PasswordNoSpecialCharHandler
import com.zoksh.feature_authentication.domain.validation.rules.PasswordNoUpperCaseHandler
import com.zoksh.feature_authentication.domain.validation.rules.PasswordTooShortHandler

class SignUpWithEmailValidationChain(
    private val name: String,
    private val email: String,
    private val password: String,
    private val confirmPassword: String
) : ValidationHandler() {
    private val head = EmptyNameHandler(name)

    override fun validate(): ValidationError? {
        head.setNext(InvalidNameHandler(name))
            .setNext(EmptyEmailHandler(email))
            .setNext(InvalidEmailHandler(email))
            .setNext(EmptyPasswordHandler(password))
            .setNext(PasswordTooShortHandler(password))
            .setNext(PasswordNoUpperCaseHandler(password))
            .setNext(PasswordNoLowerCaseHandler(password))
            .setNext(PasswordNoNumberHandler(password))
            .setNext(PasswordNoSpecialCharHandler(password))
            .setNext(PasswordNoMatchingHandler(password, confirmPassword))
        return head.handle()
    }
}