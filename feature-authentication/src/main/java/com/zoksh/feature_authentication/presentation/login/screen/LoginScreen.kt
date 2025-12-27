package com.zoksh.feature_authentication.presentation.login.screen

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
import androidx.compose.ui.unit.dp
import com.zoksh.feature_authentication.presentation.component.AuthSwitchSection
import com.zoksh.feature_authentication.presentation.component.DividerWithText
import com.zoksh.feature_authentication.presentation.component.EmailTextFieldSection
import com.zoksh.feature_authentication.presentation.component.GuestAction
import com.zoksh.feature_authentication.presentation.component.HeaderSection
import com.zoksh.feature_authentication.presentation.component.OptionsRow
import com.zoksh.feature_authentication.presentation.component.PasswordTextFieldSection
import com.zoksh.feature_authentication.presentation.component.PrimaryAction
import com.zoksh.feature_authentication.presentation.component.SocialAuthSection
import com.zoksh.feature_authentication.presentation.component.TitleSection
import com.zoksh.feature_authentication.presentation.login.contract.LoginContract
import com.zoksh.feature_authentication.presentation.login.viewmodel.LoginViewModel


@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    innerPadding: PaddingValues
) {
    val colors = MaterialTheme.colorScheme

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
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
            value = email,
            onValueChange = {
                email = it
            },
            label = "Email Address",
            placeholder = "email@example.com",
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
            isError = false,
            errorText = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        OptionsRow(
            isCheck = isChecked,
            onCheckedChange = {
                isChecked = it
            },
            onForgotPasswordClick = {

            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryAction(
            text = "Sign In",
            enabled = true,
            onClick = {

            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        DividerWithText(
            text = "Or continue with"
        )
        Spacer(modifier = Modifier.height(16.dp))
        SocialAuthSection(
            onGoogleClick = {

            },
            onFacebookClick = {

            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        DividerWithText(
            text = "Or"
        )
        Spacer(modifier = Modifier.height(16.dp))
        GuestAction(
            onClick = {

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