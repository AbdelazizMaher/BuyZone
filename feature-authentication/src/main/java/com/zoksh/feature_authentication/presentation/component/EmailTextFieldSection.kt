package com.zoksh.feature_authentication.presentation.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EmailTextFieldSection(
    value: String,
    onValueChange: (String) -> Unit,
    onFocusLost: (() -> Unit),
    label: String ,
    placeholder: String,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    isError: Boolean = false,
    errorText: String? = null
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        onFocusLost = onFocusLost,
        label = label,
        placeholder = placeholder,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        isError = isError,
        errorText = errorText
    )
}