package com.zoksh.feature_authentication.presentation.signup.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.zoksh.feature_authentication.presentation.component.AuthSwitchSection
import com.zoksh.feature_authentication.presentation.component.EmailTextFieldSection
import com.zoksh.feature_authentication.presentation.component.HeaderSection
import com.zoksh.feature_authentication.presentation.component.NameTextFieldSection
import com.zoksh.feature_authentication.presentation.component.PasswordRequirementsSection
import com.zoksh.feature_authentication.presentation.component.PasswordTextFieldSection
import com.zoksh.feature_authentication.presentation.component.PrimaryAction
import com.zoksh.feature_authentication.presentation.component.TermsAndConditions
import com.zoksh.feature_authentication.presentation.component.TitleSection
import com.zoksh.feature_authentication.presentation.model.PasswordRequirement


@Composable
fun SignupScreen(
) {
    val colors = MaterialTheme.colorScheme

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .navigationBarsPadding()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        HeaderSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        TitleSection(
            title = "Create Account",
            subtitle = "Join us and start shopping"
        )
        Spacer(modifier = Modifier.height(16.dp))
        NameTextFieldSection(
            value = name,
            onValueChange = {
                name = it
            },
            label = "Full Name",
            placeholder = "Abdelaziz Maher",
            isError = false,
            errorText = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        EmailTextFieldSection(
            value = email,
            onValueChange = {
                email = it
            },
            label = "Email Address",
            placeholder = "example@gmail.com",
            singleLine = true,
            isError = false,
            errorText = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextFieldSection(
            value = password,
            onValueChange = {
                password = it
            },
            label = "Password",
            placeholder = "**********",
            trailingIcon = {
                if (isPasswordVisible) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = null,
                        tint = colors.onBackground
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.VisibilityOff,
                        contentDescription = null,
                        tint = colors.onBackground
                    )
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            isError = false,
            errorText = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextFieldSection(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            label = "Confirm Password",
            placeholder = "**********",
            trailingIcon = {
                if (isConfirmPasswordVisible) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = null,
                        tint = colors.onBackground
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.VisibilityOff,
                        contentDescription = null,
                        tint = colors.onBackground
                    )
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            isError = false,
            errorText = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordRequirementsSection(
            requirements = listOf(
                PasswordRequirement(
                    text = "At least 8 characters",
                    isSatisfied = false
                ),
                PasswordRequirement(
                    text = "At least one uppercase letter",
                    isSatisfied = false
                ),
                PasswordRequirement(
                    text = "At least one lowercase letter",
                    isSatisfied = false
                ),
                PasswordRequirement(
                    text = "At least one number",
                    isSatisfied = false
                )
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        TermsAndConditions(
            isChecked = isChecked,
            onCheckedChange = {
                isChecked = it
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryAction(
            text = "Sign Up",
            enabled = true,
            onClick = {

            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthSwitchSection(
            text = "Already have an account?",
            actionText = "Sign In",
            onActionClick = {

            }
        )
    }
}