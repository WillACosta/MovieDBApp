package com.will.moviedbapp.network

import com.will.moviedbapp.network.services.MovieDBService
import com.will.moviedbapp.network.utils.NetworkHelper
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    fun load() {
        loadKoinModules(
            listOf(
                servicesModule(),
                networkHelperModule(),
            )
        )
    }

    private fun servicesModule(): Module = module {
        single { NetworkHelper.createService<MovieDBService>(get(), get()) }
    }

    private fun networkHelperModule() = module {
        single { GsonConverterFactory.create() }
        single { NetworkHelper.createHttpClient() }
    }

}
