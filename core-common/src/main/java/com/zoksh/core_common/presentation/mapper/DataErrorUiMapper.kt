package com.zoksh.core_common.presentation.mapper

import com.zoksh.core_common.R
import com.zoksh.core_common.domain.error.DataError
import com.zoksh.core_common.presentation.ui_text.UiText


fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Network.NO_INTERNET -> UiText.StringResource(R.string.error_no_internet)
        DataError.Network.SERVER_ERROR -> UiText.StringResource(R.string.error_server)
        DataError.Network.SERIALIZATION -> UiText.StringResource(R.string.error_serialization)
        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(R.string.error_timeout)
        DataError.Network.UNKNOWN -> UiText.StringResource(R.string.error_unknown)
        DataError.Local.DISK_FULL -> UiText.StringResource(R.string.error_disk_full)
        DataError.Local.UNKNOWN -> UiText.StringResource(R.string.error_unknown)
    }
}
