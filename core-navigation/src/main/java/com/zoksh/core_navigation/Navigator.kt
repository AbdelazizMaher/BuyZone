package com.zoksh.core_navigation

interface Navigator {
    fun navigate(
        destination: AppDestination,
        navOptions: NavOptions = NavOptions()
    )
    fun pop()
    fun popTo(
        destination: AppDestination,
        inclusive: Boolean = false
    )
    fun resetTo(
        destination: AppDestination
    )
    fun handleDeepLink(deepLink: String): Boolean
}
