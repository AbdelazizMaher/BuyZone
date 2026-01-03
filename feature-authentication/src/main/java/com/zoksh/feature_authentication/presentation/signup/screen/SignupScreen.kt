package com.zoksh.feature_authentication.presentation.signup.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.zoksh.feature_authentication.presentation.signup.contract.SignupContract
import com.zoksh.feature_authentication.presentation.signup.viewmodel.SignupViewModel


@Composable
fun SignupScreen(
    viewModel: SignupViewModel,
    innerPadding: PaddingValues
) {
    val isPasswordVisible by remember { mutableStateOf(false) }
    val isConfirmPasswordVisible by remember { mutableStateOf(false) }

    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
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
            value = state.name,
            onValueChange = {
                viewModel.handleIntent(SignupContract.Intent.NameChanged(it))
            },
            onFocusLost = {
                viewModel.handleIntent(SignupContract.Intent.NameFocusLost)
            },
            label = "Full Name",
            placeholder = "Abdelaziz Maher",
            isError = state.nameTouched && state.nameError != null,
            errorText = state.nameError
        )
        Spacer(modifier = Modifier.height(16.dp))
        EmailTextFieldSection(
            value = state.email,
            onValueChange = {
                viewModel.handleIntent(SignupContract.Intent.EmailChanged(it))
            },
            onFocusLost = {
                viewModel.handleIntent(SignupContract.Intent.EmailFocusLost)
            },
            label = "Email Address",
            placeholder = "example@gmail.com",
            isError = state.emailTouched && state.emailError != null,
            errorText = state.emailError
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextFieldSection(
            value = state.password,
            onValueChange = {
                viewModel.handleIntent(SignupContract.Intent.PasswordChanged(it))
            },
            onFocusLost = {
                viewModel.handleIntent(SignupContract.Intent.PasswordFocusLost)
            },
            label = "Password",
            placeholder = "**********",
            trailingIcon = {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            },
            visualTransformation = if (isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            isError = state.passwordTouched && state.passwordError != null,
            errorText = state.passwordError
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextFieldSection(
            value = state.confirmPassword,
            onValueChange = {
                viewModel.handleIntent(SignupContract.Intent.ConfirmPasswordChanged(it))
            },
            onFocusLost = {
                viewModel.handleIntent(SignupContract.Intent.ConfirmPasswordFocusLost)
            },
            label = "Confirm Password",
            placeholder = "**********",
            trailingIcon = {
                Icon(
                    imageVector = if (isConfirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            },
            visualTransformation = if (isConfirmPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            isError = state.confirmPasswordTouched && state.confirmPasswordError != null,
            errorText = state.confirmPasswordError
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordRequirementsSection(
            requirements = listOf(
                PasswordRequirement(
                    text = "At least 8 characters",
                    isSatisfied = state.passwordRequirements.minLength
                ),
                PasswordRequirement(
                    text = "At least one uppercase letter",
                    isSatisfied = state.passwordRequirements.upperCase
                ),
                PasswordRequirement(
                    text = "At least one lowercase letter",
                    isSatisfied = state.passwordRequirements.lowerCase
                ),
                PasswordRequirement(
                    text = "At least one number",
                    isSatisfied = state.passwordRequirements.number
                )
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        TermsAndConditions(
            text = "I agree to the Terms and Conditions",
            isChecked = state.termsAccepted,
            onCheckedChange = {
                viewModel.handleIntent(SignupContract.Intent.TermsAccepted(it))
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryAction(
            text = "Sign Up",
            enabled = true,
            onClick = {
                viewModel.handleIntent(SignupContract.Intent.Signup)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthSwitchSection(
            text = "Already have an account?",
            actionText = "Sign In",
            onActionClick = {
                viewModel.handleIntent(SignupContract.Intent.Login)
            }
        )
    }
}