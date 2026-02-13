package com.zoksh.core_common.domain.result

import com.zoksh.core_common.domain.error.RootError


sealed interface Result<out D, out E : RootError> {
    data class Success<out D, out E : RootError>(val data: D) : Result<D, E>
    data class Error<out D, out E : RootError>(val error: E) : Result<D, E>
}


inline fun <D, E : RootError, R> Result<D, E>.map(transform: (D) -> R): Result<R, E> {
    return when (this) {
        is Result.Success -> Result.Success(transform(data))
        is Result.Error -> Result.Error(error)
    }
}

inline fun <D, E : RootError, R : RootError> Result<D, E>.mapError(transform: (E) -> R): Result<D, R> {
    return when (this) {
        is Result.Success -> Result.Success(data)
        is Result.Error -> Result.Error(transform(error))
    }
}

inline fun <D, E : RootError, R> Result<D, E>.flatMap(transform: (D) -> Result<R, E>): Result<R, E> {
    return when (this) {
        is Result.Success -> transform(data)
        is Result.Error -> Result.Error(error)
    }
}


inline fun <D, E : RootError> Result<D, E>.onSuccess(action: (D) -> Unit): Result<D, E> {
    if (this is Result.Success) action(data)
    return this
}

inline fun <D, E : RootError> Result<D, E>.onError(action: (E) -> Unit): Result<D, E> {
    if (this is Result.Error) action(error)
    return this
}


inline fun <D, E : RootError, R> Result<D, E>.fold(
    onSuccess: (D) -> R,
    onError: (E) -> R
): R {
    return when (this) {
        is Result.Success -> onSuccess(data)
        is Result.Error -> onError(error)
    }
}


fun <D, E : RootError> Result<D, E>.asEmptySuccess(): Result<Unit, E> {
    return map { }
}
