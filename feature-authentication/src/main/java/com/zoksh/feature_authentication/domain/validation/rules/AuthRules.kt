package com.zoksh.feature_authentication.domain.validation.rules

import com.zoksh.feature_authentication.domain.model.ValidationError
import com.zoksh.feature_authentication.domain.validation.ValidationHandler

class EmptyEmailHandler(private val email: String) : ValidationHandler() {
    override fun validate() = if (email.isBlank()) ValidationError.EmptyEmail
    else null
}

class EmptyPasswordHandler(private val password: String) : ValidationHandler() {
    override fun validate() = if (password.isBlank()) ValidationError.EmptyPassword
    else null
}

class EmptyNameHandler(private val name: String) : ValidationHandler() {
    override fun validate() = if (name.isBlank()) ValidationError.EmptyName
    else null
}

class InvalidEmailHandler(private val email: String) : ValidationHandler() {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    override fun validate() = if (!emailRegex.matches(email)) ValidationError.InvalidEmail
    else null
}

class InvalidNameHandler(private val name: String) : ValidationHandler() {
    override fun validate(): ValidationError? {
        val parts = name.trim().split("\\s+".toRegex())
        if (parts.size < 2) return ValidationError.InvalidName
        if (parts.any { it.length < 2 }) return ValidationError.InvalidName
        return null
    }
}

class PasswordTooShortHandler(private val password: String, private val minLength: Int = 8) :
    ValidationHandler() {
    override fun validate() = if (password.length < minLength) ValidationError.PasswordTooShort
    else null
}

class PasswordNoUpperCaseHandler(private val password: String) : ValidationHandler() {
    override fun validate() =
        if (password.none { it.isUpperCase() }) ValidationError.PasswordNoUpperCase
        else null
}

class PasswordNoLowerCaseHandler(private val password: String) : ValidationHandler() {
    override fun validate() =
        if (password.none { it.isLowerCase() }) ValidationError.PasswordNoLowerCase
        else null
}

class PasswordNoNumberHandler(private val password: String) : ValidationHandler() {
    override fun validate() = if (password.none { it.isDigit() }) ValidationError.PasswordNoNumber
    else null
}

class PasswordNoSpecialCharHandler(private val password: String) : ValidationHandler() {
    override fun validate() =
        if (password.none { !it.isLetterOrDigit() }) ValidationError.PasswordNoSpecialChar
        else null
}

class PasswordNoMatchingHandler(private val password: String, private val confirmPassword: String) :
    ValidationHandler() {
    override fun validate() = if (password != confirmPassword) ValidationError.PasswordNoMatching
    else null
}
