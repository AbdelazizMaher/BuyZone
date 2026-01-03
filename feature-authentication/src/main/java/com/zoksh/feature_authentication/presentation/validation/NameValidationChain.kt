package com.zoksh.feature_authentication.presentation.validation

import com.zoksh.feature_authentication.domain.validation.ValidationHandler
import com.zoksh.feature_authentication.domain.validation.rules.EmptyNameHandler
import com.zoksh.feature_authentication.domain.validation.rules.InvalidNameHandler

object NameValidationChain {
    fun build(name: String): ValidationHandler {
        val head = EmptyNameHandler(name).apply {
            setNext(InvalidNameHandler(name))
        }
        return head
    }
}