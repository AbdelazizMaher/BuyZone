package com.zoksh.feature_authentication.presentation.component

import androidx.compose.runtime.Composable

@Composable
fun EmailTextFieldSection(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit) ,
    singleLine: Boolean ,
    isError: Boolean = false,
    errorText: String? = null
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        singleLine = singleLine,
        isError = isError,
        errorText = errorText
    )
}