package com.zoksh.feature_authentication.domain.validation

import com.zoksh.feature_authentication.domain.model.ValidationError

abstract class ValidationHandler {
    var next: ValidationHandler? = null

    fun setNext(handler: ValidationHandler): ValidationHandler {
        this.next = handler
        return handler
    }

    fun handle(): ValidationError? {
        return validate() ?: next?.handle()
    }

    abstract fun validate() : ValidationError?
}