package com.zoksh.feature_authentication.presentation.signup.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zoksh.core_ui.components.PrimaryActionButton
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.feature_authentication.presentation.component.AuthSwitchSection
import com.zoksh.feature_authentication.presentation.component.EmailTextFieldSection
import com.zoksh.feature_authentication.presentation.component.HeaderSection
import com.zoksh.feature_authentication.presentation.component.NameTextFieldSection
import com.zoksh.feature_authentication.presentation.component.PasswordRequirementsSection
import com.zoksh.feature_authentication.presentation.component.PasswordTextFieldSection
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
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    SignupContent(
        state = state,
        onIntent = { viewModel.handleIntent(it) },
        innerPadding = innerPadding
    )
}

@Composable
fun SignupContent(
    state: SignupContract.State,
    onIntent: (SignupContract.Intent) -> Unit,
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

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
                .padding(top = 40.dp)
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
                onIntent(SignupContract.Intent.NameChanged(it))
            },
            onFocusLost = {
                onIntent(SignupContract.Intent.NameFocusLost)
            },
            label = "Full Name",
            placeholder = "Abdelaziz Maher",
            isError = (state.nameTouched || state.submitAttempted) && state.nameError != null,
            errorText = state.nameError
        )
        Spacer(modifier = Modifier.height(16.dp))
        EmailTextFieldSection(
            value = state.email,
            onValueChange = {
                onIntent(SignupContract.Intent.EmailChanged(it))
            },
            onFocusLost = {
                onIntent(SignupContract.Intent.EmailFocusLost)
            },
            label = "Email Address",
            placeholder = "example@gmail.com",
            isError = (state.emailTouched || state.submitAttempted) && state.emailError != null,
            errorText = state.emailError
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextFieldSection(
            value = state.password,
            onValueChange = {
                onIntent(SignupContract.Intent.PasswordChanged(it))
            },
            onFocusLost = {
                onIntent(SignupContract.Intent.PasswordFocusLost)
            },
            label = "Password",
            placeholder = "**********",
            trailingIcon = {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    modifier = Modifier
                        .clickable { isPasswordVisible = !isPasswordVisible },
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = (state.passwordTouched || state.submitAttempted) && state.passwordError != null,
            errorText = state.passwordError
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextFieldSection(
            value = state.confirmPassword,
            onValueChange = {
                onIntent(SignupContract.Intent.ConfirmPasswordChanged(it))
            },
            onFocusLost = {
                onIntent(SignupContract.Intent.ConfirmPasswordFocusLost)
            },
            label = "Confirm Password",
            placeholder = "**********",
            trailingIcon = {
                Icon(
                    imageVector = if (isConfirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    modifier = Modifier
                        .clickable { isConfirmPasswordVisible = !isConfirmPasswordVisible },
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            },
            visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = (state.confirmPasswordTouched || state.submitAttempted) && state.confirmPasswordError != null,
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
                onIntent(SignupContract.Intent.TermsAccepted(it))
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryActionButton(
            text = "Sign Up",
            enabled = !state.signupClicked,
            onClick = {
                onIntent(SignupContract.Intent.Signup)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthSwitchSection(
            text = "Already have an account?",
            actionText = "Sign In",
            onActionClick = {
                onIntent(SignupContract.Intent.Login)
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun SignupScreenPreview() {
    BuyZoneTheme {
        Scaffold { innerPadding ->
            SignupContent(
                state = SignupContract.State(),
                onIntent = {},
                innerPadding = innerPadding
            )
        }
    }
}
