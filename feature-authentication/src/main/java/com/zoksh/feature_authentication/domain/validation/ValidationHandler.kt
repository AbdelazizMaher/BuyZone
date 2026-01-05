package com.zoksh.feature_authentication.domain.validation

import com.zoksh.feature_authentication.domain.model.ValidationError

abstract class ValidationHandler {
    var next: ValidationHandler? = null

    fun setNext(handler: ValidationHandler): ValidationHandler {
        this.next = handler
        return handler
    }

    fun handleFirstError() : ValidationError? {
        return validate() ?: next?.handleFirstError()
    }

    fun handleAllErrors() : List<ValidationError> {
        val errors = mutableListOf<ValidationError>()
        validate()?.let { errors.add(it) }
        return errors + (next?.handleAllErrors() ?: emptyList())
    }

    abstract fun validate() : ValidationError?
}