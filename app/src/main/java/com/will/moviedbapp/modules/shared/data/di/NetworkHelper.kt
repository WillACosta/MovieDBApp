package com.will.moviedbapp.modules.shared.data.di

import android.util.Log
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.core.utils.interceptors.ApiInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {

    inline fun <reified T> createService(
        factory: GsonConverterFactory,
        client: OkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(AppConstants.Network.BASE_URL)
            .addConverterFactory(factory)
            .client(client)
            .build()
            .create(T::class.java)
    }

    fun createHttpClient(): OkHttpClient {
        val loggingInterceptor = createLoggingInterceptor()

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiInterceptor())
            .build()
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor {
            Log.i(AppConstants.Network.LOG_RESPONSE_TAG, it)
        }

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}
