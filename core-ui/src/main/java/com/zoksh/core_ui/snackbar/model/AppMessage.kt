package com.zoksh.core_ui.snackbar.model

data class AppMessage(
    val text: String,
    val type: MessageType,
    val action: AppMessageAction? = null
)

enum class MessageType {
    INFO,
    SUCCESS,
    WARNING,
    ERROR
}

data class AppMessageAction(
    val label: String,
    val onClick: () -> Unit
)
