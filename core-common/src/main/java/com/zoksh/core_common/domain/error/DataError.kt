package com.zoksh.core_common.domain.error


sealed interface DataError : RootError {
    enum class Network : DataError {
        NO_INTERNET,
        SERVER_ERROR,
        SERIALIZATION,
        REQUEST_TIMEOUT,
        UNKNOWN
    }
    enum class Local : DataError {
        DISK_FULL,
        UNKNOWN
    }
}
