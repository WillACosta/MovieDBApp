package com.will.moviedbapp.data.utils

import okhttp3.ResponseBody
import java.io.IOException

sealed class Resource<out T> {

    data class Success<T>(val data: T) : Resource<T>()

    data class Error(
        val exception: Throwable,
        val statusCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>() {
        val isNetworkError: Boolean get() = exception is IOException
    }

    object Empty : Resource<Nothing>()

    object Loading : Resource<Nothing>()

    companion object {
        fun <T> success(data: T) = Success(data)

        fun error(exception: Throwable) = Error(exception)

        fun empty() = Empty

        fun loading() = Loading

        fun <T> successOrEmpty(list: List<T>): Resource<List<T>> {
            return if (list.isEmpty()) Empty else Success(list)
        }
    }
}
