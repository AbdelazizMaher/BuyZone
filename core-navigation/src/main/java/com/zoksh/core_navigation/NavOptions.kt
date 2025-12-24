package com.zoksh.core_navigation

data class NavOptions(
    val launchSingleTop: Boolean = false,
    val popUpTo: AppDestination? = null,
    val popUpToInclusive: Boolean = false,
    val saveState: Boolean = false,
    val restoreState: Boolean = false
)
