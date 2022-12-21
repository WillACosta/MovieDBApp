package com.will.moviedbapp.core.state

import java.io.IOException

sealed class StateResult<out T> {

    data class Success<T>(val data: T) : StateResult<T>()

    data class Error(val exception: Throwable) : StateResult<Nothing>() {
        val isNetworkError: Boolean get() = exception is IOException
    }

    object Empty : StateResult<Nothing>()

    object Loading : StateResult<Nothing>()

    companion object {
        fun <T> success(data: T) = Success(data)

        fun error(exception: Throwable) = Error(exception)

        fun empty() = Empty

        fun loading() = Loading

        fun <T> successOrEmpty(list: List<T>): StateResult<List<T>> {
            return if (list.isEmpty()) Empty else Success(list)
        }
    }
}
