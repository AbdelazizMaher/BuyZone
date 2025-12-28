package com.zoksh.feature_authentication.domain.validation.chains

import com.zoksh.feature_authentication.domain.model.ValidationError
import com.zoksh.feature_authentication.domain.validation.ValidationHandler
import com.zoksh.feature_authentication.domain.validation.rules.EmptyEmailHandler
import com.zoksh.feature_authentication.domain.validation.rules.EmptyPasswordHandler
import com.zoksh.feature_authentication.domain.validation.rules.InvalidEmailHandler

class LoginWithEmailValidationChain(
    private val email: String,
    private val password: String
): ValidationHandler() {
    private val head  = EmptyEmailHandler(email)

    override fun validate(): ValidationError? {
        head.setNext(EmptyPasswordHandler(password))
            .setNext(InvalidEmailHandler(email))
        return head.handle()
    }
}