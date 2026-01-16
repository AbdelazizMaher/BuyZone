package com.zoksh.buyzone.bottombar

import com.zoksh.buyzone.R
import com.zoksh.feature_authentication.presentation.navigation.AuthDestination
import com.zoksh.feature_home.presentation.navigation.HomeDestination
import com.zoksh.feature_onboarding.presentation.navigation.OnBoardingDestination


sealed class NavItem(
    val route: Any,
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
) {
    object Home : NavItem(
        HomeDestination.Home,
        "Home",
        R.drawable.ic_home_filled,
        R.drawable.ic_home
    )

    object Search : NavItem(
        OnBoardingDestination.OnBoarding,
        "Search",
        R.drawable.ic_search_filled,
        R.drawable.ic_search
    )

    object Cart :
        NavItem(
            AuthDestination.Login,
            "Cart",
            R.drawable.ic_cart_filled,
            R.drawable.ic_cart
        )

    object Settings : NavItem(
        AuthDestination.SignUp,
        "Settings",
        R.drawable.ic_settings_filled,
        R.drawable.ic_settings
    )
}
