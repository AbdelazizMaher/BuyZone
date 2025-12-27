package com.zoksh.feature_authentication.presentation.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordTextFieldSection(
    value: String,
    onValueChange: (String) -> Unit,
    label: String ,
    placeholder: String,
    trailingIcon: @Composable (() -> Unit),
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    visualTransformation: VisualTransformation = PasswordVisualTransformation(),
    singleLine: Boolean = true,
    isError: Boolean = false,
    errorText: String? = null
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        placeholder = placeholder,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        isError = isError,
        errorText = errorText
    )
}