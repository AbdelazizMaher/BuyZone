package com.zoksh.feature_authentication.presentation.login.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zoksh.core_navigation.Navigator
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



@Composable
fun LoginScreen(
    navigator: Navigator
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
            .statusBarsPadding()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        HeaderSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
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
            placeholder = "Email Address",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = colors.onBackground
                )
            },
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
            placeholder = "Password",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = colors.onBackground
                )
            },
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

            }
        )
    }
}