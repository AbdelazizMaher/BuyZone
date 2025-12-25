package com.zoksh.feature_authentication.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class PasswordRequirement(
    val text: String,
    val isSatisfied: Boolean
)
