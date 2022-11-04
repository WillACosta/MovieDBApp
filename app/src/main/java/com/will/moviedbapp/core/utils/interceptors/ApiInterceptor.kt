package com.will.moviedbapp.core.utils.interceptors

import com.will.moviedbapp.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiInterceptor : Interceptor {
    private val apiKey = BuildConfig.API_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        return chain.proceed(request.newBuilder().url(url).build())
    }
}
