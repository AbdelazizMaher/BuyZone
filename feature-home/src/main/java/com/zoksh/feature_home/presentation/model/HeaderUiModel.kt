package com.zoksh.feature_home.presentation.model

data class HeaderUiModel(
    val image: String? = null,
    val message: String = "Welcome back 👋",
    val name: String = "",
    val notificationCount: Int = 0
)
