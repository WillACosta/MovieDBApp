package com.will.moviedbapp.data.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend inline fun <T> safeApiCall(
    crossinline responseCallback: suspend () -> T
): Result<T> {
    return try {
        val response = withContext(Dispatchers.IO) {
            responseCallback()
        }

        Result.success(response)

    } catch (ex: Throwable) {
        Result.failure(ex)
    }
}
