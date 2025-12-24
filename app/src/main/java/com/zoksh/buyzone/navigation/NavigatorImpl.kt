package com.zoksh.buyzone.navigation

import com.zoksh.core_navigation.AppDestination
import com.zoksh.core_navigation.NavOptions
import com.zoksh.core_navigation.Navigator

class NavigatorImpl(

): Navigator {
    override fun navigate(
        destination: AppDestination,
        navOptions: NavOptions
    ) {
        TODO("Not yet implemented")
    }

    override fun pop() {
        TODO("Not yet implemented")
    }

    override fun popTo(
        destination: AppDestination,
        inclusive: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun resetTo(destination: AppDestination) {
        TODO("Not yet implemented")
    }

    override fun handleDeepLink(deepLink: String): Boolean {
        TODO("Not yet implemented")
    }
}