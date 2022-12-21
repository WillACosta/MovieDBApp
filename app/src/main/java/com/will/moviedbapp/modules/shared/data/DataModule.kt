package com.will.moviedbapp.modules.shared.data

import com.will.moviedbapp.modules.shared.data.datasource.remote.movie.CMovieDBRemoteDataSource
import com.will.moviedbapp.modules.shared.data.datasource.remote.movie.MovieDBRemoteDataSource
import com.will.moviedbapp.modules.shared.data.repository.local.userPreferences.CUserPreferencesRepository
import com.will.moviedbapp.modules.shared.data.repository.local.userPreferences.UserPreferencesRepository
import com.will.moviedbapp.modules.shared.data.repository.remote.movie.CMovieRepository
import com.will.moviedbapp.modules.shared.data.repository.remote.movie.MovieRepository
import com.will.moviedbapp.modules.shared.data.services.MovieDBService
import com.will.moviedbapp.modules.shared.data.utils.NetworkHelper
import com.will.moviedbapp.modules.shared.data.utils.dataStore
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    fun load() {
        loadKoinModules(
            listOf(
                networkModule(),
                servicesModule(),
                dataSourcesModule(),
                repositoryModule()
            )
        )
    }

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

    private fun repositoryModule(): Module {
        return module {
            factory<MovieRepository> { CMovieRepository(get()) }

            single { androidApplication().dataStore }

            single<UserPreferencesRepository> { CUserPreferencesRepository(get()) }
        }
    }
}
