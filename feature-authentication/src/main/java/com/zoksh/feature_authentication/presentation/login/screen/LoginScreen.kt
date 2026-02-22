package com.zoksh.feature_authentication.presentation.login.screen

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
import com.zoksh.feature_authentication.presentation.component.DividerWithText
import com.zoksh.feature_authentication.presentation.component.EmailTextFieldSection
import com.zoksh.feature_authentication.presentation.component.GuestAction
import com.zoksh.feature_authentication.presentation.component.HeaderSection
import com.zoksh.feature_authentication.presentation.component.OptionsRow
import com.zoksh.feature_authentication.presentation.component.PasswordTextFieldSection
import com.zoksh.core_ui.components.PrimaryActionButton
import com.zoksh.feature_authentication.presentation.component.SocialAuthSection
import com.zoksh.feature_authentication.presentation.component.TitleSection
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract
import com.zoksh.feature_authentication.presentation.login.viewmodel.LoginViewModel


@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    innerPadding: PaddingValues
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
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
            title = "Welcome Back",
            subtitle = "Sign in to continue shopping"
        )
        Spacer(modifier = Modifier.height(16.dp))
        EmailTextFieldSection(
            value = state.email,
            onValueChange = {
                viewModel.handleIntent(LoginContract.Intent.EmailChanged(it))
            },
            onFocusLost = {
                viewModel.handleIntent(LoginContract.Intent.EmailFocusLost)
            },
            label = "Email Address",
            placeholder = "email@example.com",
            isError = (state.emailTouched || state.submitAttempted) && state.emailError != null,
            errorText = state.emailError
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextFieldSection(
            value = state.password,
            onValueChange = {
                viewModel.handleIntent(LoginContract.Intent.PasswordChanged(it))
            },
            onFocusLost = {
                viewModel.handleIntent(LoginContract.Intent.PasswordFocusLost)
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
            isError = state.passwordTouched && state.passwordError != null,
            errorText = state.passwordError
        )
        Spacer(modifier = Modifier.height(16.dp))
        OptionsRow(
            isChecked = state.rememberMe,
            onRememberMeClick = {
                viewModel.handleIntent(LoginContract.Intent.RememberMe(it))
            },
            onForgotPasswordClick = {
                viewModel.handleIntent(LoginContract.Intent.ForgotPassword)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryActionButton(
            text = "Sign In",
            enabled = !state.loginClicked,
            onClick = {
                viewModel.handleIntent(LoginContract.Intent.SignIn)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        DividerWithText(
            text = "Or continue with"
        )
        Spacer(modifier = Modifier.height(16.dp))
        SocialAuthSection(
            onGoogleClick = {
                viewModel.handleIntent(LoginContract.Intent.GoogleLogin)
            },
            onFacebookClick = {
                viewModel.handleIntent(LoginContract.Intent.FacebookLogin)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        DividerWithText(
            text = "Or"
        )
        Spacer(modifier = Modifier.height(16.dp))
        GuestAction(
            onClick = {
                viewModel.handleIntent(LoginContract.Intent.GuestAccess)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthSwitchSection(
            text = "Don't have an account?",
            actionText = "Sign Up",
            onActionClick = {
                viewModel.handleIntent(LoginContract.Intent.SignUp)
            }
        )
    }
}