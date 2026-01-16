package com.zoksh.buyzone.bottombar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppBottomBar(
    navController: NavHostController,
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
    val navItems = listOf(
        NavItem.Home,
        NavItem.Search,
        NavItem.Cart
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(WindowInsets.navigationBars.asPaddingValues())
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(28.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                navItems.forEach { item ->
                    val routeName = item.route::class.simpleName.toString()
                    val isSelected = currentDestination?.contains(routeName) == true

                    BottomBarItem(
                        label = item.title,
                        selected = isSelected,
                        selectedIcon = painterResource(item.selectedIcon),
                        unselectedIcon = painterResource(item.unselectedIcon),
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(NavItem.Home.route) {
                                    inclusive = false
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
        Spacer(Modifier.width(12.dp))
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp
        ) {
            val isSettingsSelected =
                currentDestination?.contains(NavItem.Settings.route::class.simpleName.toString()) == true
            BottomBarItem(
                label = NavItem.Settings.title,
                selected = isSettingsSelected,
                selectedIcon = painterResource(NavItem.Settings.selectedIcon),
                unselectedIcon = painterResource(NavItem.Settings.unselectedIcon),
                onClick = {
                    navController.navigate(NavItem.Settings.route) {
                        popUpTo(NavItem.Home.route) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}