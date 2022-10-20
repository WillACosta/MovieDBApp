package com.will.moviedbapp.data.di

import android.util.Log
import com.will.moviedbapp.core.constants.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private fun networkModule(): Module {
        return module {
            single { GsonConverterFactory.create() }
            single { createHttpClient() }
        }
    }

    private inline fun <reified T> createService(
        factory: GsonConverterFactory,
        client: OkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(AppConstants.NETWORK.BASE_URL)
            .addConverterFactory(factory)
            .client(client)
            .build()
            .create(T::class.java)
    }

    private fun createHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor {
            Log.i(AppConstants.NETWORK.LOG_RESPONSE_TAG, it)
        }

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

}
