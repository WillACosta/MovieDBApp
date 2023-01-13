package com.will.moviedbapp.data.utils

import java.io.IOException

sealed class ResponseResult<out T> {
    data class Success<T>(val data: T) : ResponseResult<T>()

    data class Failure(
        val exception: Throwable
    ) : ResponseResult<Nothing>() {
        val isNetworkError: Boolean get() = exception is IOException
    }

    object Loading : ResponseResult<Nothing>()
    object Complete : ResponseResult<Nothing>()
}
