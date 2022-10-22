package com.will.moviedbapp.data.di

import com.will.moviedbapp.data.datasource.movieDb.CMovieDBRemoteDataSource
import com.will.moviedbapp.data.datasource.movieDb.MovieDBRemoteDataSource
import com.will.moviedbapp.data.services.MovieDBService
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    private fun networkModule(): Module {
        return module {
            single { GsonConverterFactory.create() }
            single { NetworkHelper.createHttpClient() }
        }
    }

    private fun servicesModule(): Module {
        return module {
            single { NetworkHelper.createService<MovieDBService>(get(), get()) }
        }
    }

    private fun dataSourcesModule(): Module {
        return module {
            factory<MovieDBRemoteDataSource> { CMovieDBRemoteDataSource(get()) }
        }
    }
}
