package com.zoksh.feature_authentication.presentation.login.screen

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
import com.zoksh.feature_authentication.presentation.component.DividerWithText
import com.zoksh.feature_authentication.presentation.component.EmailTextFieldSection
import com.zoksh.feature_authentication.presentation.component.GuestAction
import com.zoksh.feature_authentication.presentation.component.HeaderSection
import com.zoksh.feature_authentication.presentation.component.OptionsRow
import com.zoksh.feature_authentication.presentation.component.PasswordTextFieldSection
import com.zoksh.feature_authentication.presentation.component.SocialAuthSection
import com.zoksh.feature_authentication.presentation.component.TitleSection
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract
import com.zoksh.feature_authentication.presentation.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    innerPadding: PaddingValues
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginContent(
        state = state,
        onIntent = { viewModel.handleIntent(it) },
        innerPadding = innerPadding
    )
}

@Composable
fun LoginContent(
    state: LoginContract.State,
    onIntent: (LoginContract.Intent) -> Unit,
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

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
                onIntent(LoginContract.Intent.EmailChanged(it))
            },
            onFocusLost = {
                onIntent(LoginContract.Intent.EmailFocusLost)
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
                onIntent(LoginContract.Intent.PasswordChanged(it))
            },
            onFocusLost = {
                onIntent(LoginContract.Intent.PasswordFocusLost)
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
                onIntent(LoginContract.Intent.RememberMe(it))
            },
            onForgotPasswordClick = {
                onIntent(LoginContract.Intent.ForgotPassword)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryActionButton(
            text = "Sign In",
            enabled = !state.loginClicked,
            onClick = {
                onIntent(LoginContract.Intent.SignIn)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        DividerWithText(
            text = "Or continue with"
        )
        Spacer(modifier = Modifier.height(16.dp))
        SocialAuthSection(
            onGoogleClick = {
                onIntent(LoginContract.Intent.GoogleLogin)
            },
            onFacebookClick = {
                onIntent(LoginContract.Intent.FacebookLogin)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        DividerWithText(
            text = "Or"
        )
        Spacer(modifier = Modifier.height(16.dp))
        GuestAction(
            onClick = {
                onIntent(LoginContract.Intent.GuestAccess)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthSwitchSection(
            text = "Don't have an account?",
            actionText = "Sign Up",
            onActionClick = {
                onIntent(LoginContract.Intent.SignUp)
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
private fun LoginScreenPreview() {
    BuyZoneTheme {
        Scaffold { innerPadding ->
            LoginContent(
                state = LoginContract.State(),
                onIntent = {},
                innerPadding = innerPadding
            )
        }
    }
}
