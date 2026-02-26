package com.zoksh.feature_settings.presentation.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Login
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.zoksh.core_ui.theme.BuyZoneTheme
import com.zoksh.feature_settings.presentation.components.ProfileHeader
import com.zoksh.feature_settings.presentation.components.QuickActionsSection
import com.zoksh.feature_settings.presentation.components.SettingsRow
import com.zoksh.feature_settings.presentation.components.SettingsSectionTitle
import com.zoksh.feature_settings.presentation.contract.SettingsContract
import com.zoksh.feature_settings.presentation.contract.UserUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    state: SettingsContract.State,
    onIntent: (SettingsContract.Intent) -> Unit,
    innerPadding: PaddingValues
) {
    val lazyListState = rememberLazyListState()

    val scrollProgress by remember {
        derivedStateOf {
            if (lazyListState.firstVisibleItemIndex > 0) 1f
            else (lazyListState.firstVisibleItemScrollOffset / 400f).coerceIn(0f, 1f)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SettingsTopBar(
                state = state,
                alpha = scrollProgress,
                onMoreClick = {  }
            )
        }
    ) { padding ->
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(
                bottom = innerPadding.calculateBottomPadding() + 24.dp
            )
        ) {
            item {
                ProfileHeader(
                    user = state.user,
                    isGuest = state.isGuest,
                    onMoreClick = {  },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = innerPadding.calculateTopPadding())
                        .alpha(1f - scrollProgress)
                )
            }

            item {
                QuickActionsSection(
                    isGuest = state.isGuest,
                    onOrdersClick = { onIntent(SettingsContract.Intent.Orders) },
                    onAddressesClick = { onIntent(SettingsContract.Intent.Addresses) },
                    onWishlistClick = { onIntent(SettingsContract.Intent.Wishlist) },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            item {
                SettingsSectionTitle(title = "Preferences")
                SettingsRow(
                    title = "Currency Selection",
                    icon = Icons.Outlined.Payments,
                    trailingValue = state.currency,
                    usePrimaryTint = true,
                    onClick = { onIntent(SettingsContract.Intent.CurrencySelection) }
                )
                SettingsRow(
                    title = "Notifications",
                    icon = Icons.Outlined.Notifications,
                    usePrimaryTint = true,
                    onClick = { onIntent(SettingsContract.Intent.Notifications) }
                )
                SettingsRow(
                    title = "App Theme",
                    icon = Icons.Outlined.Palette,
                    trailingValue = state.appTheme,
                    usePrimaryTint = true,
                    onClick = { onIntent(SettingsContract.Intent.AppTheme) }
                )
            }

            item {
                SettingsSectionTitle(title = "Support")
                SettingsRow(
                    title = "About Us",
                    icon = Icons.Outlined.Info,
                    usePrimaryTint = true,
                    onClick = { onIntent(SettingsContract.Intent.AboutUs) }
                )
                SettingsRow(
                    title = "Help / Contact",
                    icon = Icons.Outlined.HelpOutline,
                    usePrimaryTint = true,
                    onClick = { onIntent(SettingsContract.Intent.HelpContact) }
                )
            }

            item {
                SettingsSectionTitle(title = "Session")
                if (state.isGuest) {
                    Box(modifier = Modifier.padding(16.dp)) {
                        Button(
                            onClick = { onIntent(SettingsContract.Intent.LoginRegister) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(
                                Icons.Outlined.Login,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text("Login / Register", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                    }
                } else {
                    SettingsRow(
                        title = "Logout",
                        icon = Icons.AutoMirrored.Outlined.Logout,
                        isDestructive = true,
                        onClick = { onIntent(SettingsContract.Intent.Logout) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsTopBar(
    state: SettingsContract.State,
    alpha: Float,
    onMoreClick: () -> Unit
) {
    val contentAlpha = (alpha - 0.5f).coerceIn(0f, 1f) * 2f

    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.alpha(contentAlpha)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    if (state.isGuest || state.user?.imageUrl == null) {
                        Icon(Icons.Outlined.Person, null, modifier = Modifier.size(20.dp))
                    } else {
                        AsyncImage(
                            model = state.user.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(Modifier.width(12.dp))
                Text(
                    text = if (state.isGuest) "Settings" else state.user?.name ?: "Settings",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        actions = {
            IconButton(
                onClick = onMoreClick,
                modifier = Modifier.alpha(contentAlpha)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = alpha),
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Preview(showBackground = true, name = "Logged In - Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Logged In - Dark")
@Composable
private fun SettingsScreenLoggedInPreview() {
    BuyZoneTheme {
        SettingsScreen(
            state = SettingsContract.State(
                isGuest = false,
                user = UserUiModel(
                    "Abdelaziz Maher",
                    "Abdelaziz.Maher@example.com",
                    "https://i.pravatar.cc/150?img=1"
                ),
                notificationCount = 5
            ),
            onIntent = {},
            innerPadding = PaddingValues(0.dp)
        )
    }
}

@Preview(showBackground = true, name = "Guest Mode - Light")
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Guest Mode - Dark"
)
@Composable
private fun SettingsScreenGuestPreview() {
    BuyZoneTheme {
        SettingsScreen(
            state = SettingsContract.State(isGuest = true),
            onIntent = {},
            innerPadding = PaddingValues(0.dp)
        )
    }
}
