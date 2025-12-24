package com.zoksh.buyzone.navigation

import android.content.Intent
import android.net.Uri
import androidx.navigation.NavHostController
import com.zoksh.core_navigation.AppDestination
import com.zoksh.core_navigation.NavOptions
import com.zoksh.core_navigation.Navigator

class NavigatorImpl(
    private val navController: NavHostController,
    private val routeMapper: RouteMapper
): Navigator {
    override fun navigate(
        destination: AppDestination,
        navOptions: NavOptions
    ) {
        navController.navigate(routeMapper.route(destination)) {
            launchSingleTop = navOptions.launchSingleTop
            restoreState = navOptions.restoreState

            navOptions.popUpTo?.let {
                popUpTo(routeMapper.route(destination)) {
                    inclusive = navOptions.popUpToInclusive
                    saveState = navOptions.saveState
                }
            }
        }
    }

    override fun pop() {
        navController.popBackStack()
    }

    override fun popTo(
        destination: AppDestination,
        inclusive: Boolean
    ) {
        navController.popBackStack(
            route = routeMapper.route(destination),
            inclusive = inclusive
        )
    }

    override fun resetTo(destination: AppDestination) {
        navController.navigate(routeMapper.route(destination)) {
            popUpTo(0)
        }
    }

    override fun handleDeepLink(deepLink: String): Boolean {
        return navController.handleDeepLink(
            Intent(Intent.ACTION_VIEW, Uri.parse(deepLink))
        )
    }
}