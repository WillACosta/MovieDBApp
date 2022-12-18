package com.will.moviedbapp.modules.shared.data.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.modules.shared.data.data_source.movie_db.CMovieDBRemoteDataSource
import com.will.moviedbapp.modules.shared.data.data_source.movie_db.MovieDBRemoteDataSource
import com.will.moviedbapp.modules.shared.data.repository.movie.CMovieRepository
import com.will.moviedbapp.modules.shared.data.repository.movie.MovieRepository
import com.will.moviedbapp.modules.shared.data.repository.userPreferences.CUserPreferencesRepository
import com.will.moviedbapp.modules.shared.data.repository.userPreferences.UserPreferencesRepository
import com.will.moviedbapp.modules.shared.data.services.MovieDBService
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

private val Context.dataStore by preferencesDataStore(
    name = AppConstants.Preferences.NAME
)

object DataModule {

    fun load() {
        return loadKoinModules(
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
