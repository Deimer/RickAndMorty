package com.testvass.repository.utils

sealed class OnResult<out R> {

    data class Success<out T>(val data: T) : OnResult<T>()
    data class Error(val exception: Throwable) : OnResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

val OnResult<*>.succeeded
    get() = this is OnResult.Success && data != null