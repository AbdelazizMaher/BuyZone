package com.zoksh.feature_authentication.presentation.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun NameTextFieldSection(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit),
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions= KeyboardOptions(keyboardType = KeyboardType.Text),
    isError: Boolean = false,
    errorText: String? = null
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        isError = isError,
        errorText = errorText
    )
}