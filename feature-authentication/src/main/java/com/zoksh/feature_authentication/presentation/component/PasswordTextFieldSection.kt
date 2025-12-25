package com.zoksh.feature_authentication.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordTextFieldSection(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit),
    trailingIcon: @Composable (() -> Unit),
    visualTransformation: VisualTransformation,
    singleLine: Boolean,
    isError: Boolean = false,
    errorText: String? = null
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        isError = isError,
        errorText = errorText
    )
}