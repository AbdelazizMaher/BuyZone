package com.zoksh.buyzone.bottombar

import com.zoksh.core_common.R
import com.zoksh.feature_authentication.presentation.navigation.AuthDestination
import com.zoksh.feature_categories.presentation.navigation.CategoriesDestination
import com.zoksh.feature_home.presentation.navigation.HomeDestination


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
        CategoriesDestination.Categories,
        "Categories",
        R.drawable.ic_category,
        R.drawable.ic_category
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
