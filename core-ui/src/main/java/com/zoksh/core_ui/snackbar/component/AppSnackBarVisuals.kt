package com.zoksh.core_ui.snackbar.component

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import com.zoksh.core_ui.snackbar.model.AppMessage

class AppSnackBarVisuals(
   val appMessage: AppMessage
): SnackbarVisuals {
    override val actionLabel: String?
        get() = appMessage.action?.label
    override val duration: SnackbarDuration
        get() = SnackbarDuration.Short
    override val message: String
        get() = appMessage.text
    override val withDismissAction: Boolean
        get() = appMessage.action == null
}