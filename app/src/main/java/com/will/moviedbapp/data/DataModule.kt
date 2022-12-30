package com.will.moviedbapp.data

import com.will.moviedbapp.data.datasource.movie.CMovieDBRemoteDataSource
import com.will.moviedbapp.data.datasource.movie.MovieDBRemoteDataSource
import com.will.moviedbapp.data.repository.user_preferences.CUserPreferencesRepository
import com.will.moviedbapp.data.repository.user_preferences.UserPreferencesRepository
import com.will.moviedbapp.data.repository.movie.CMovieRepository
import com.will.moviedbapp.data.repository.movie.MovieRepository
import com.will.moviedbapp.network.utils.dataStore
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DataModule {
    fun load() {
        loadKoinModules(
            listOf(
                repositoryModule(),
                dataSourcesModule()
            )
        )
    }

    private fun repositoryModule(): Module = module {
        factory<MovieRepository> { CMovieRepository(get()) }
        single { androidApplication().dataStore }
        single<UserPreferencesRepository> { CUserPreferencesRepository(get()) }
    }

    private fun dataSourcesModule(): Module = module {
        factory<MovieDBRemoteDataSource> { CMovieDBRemoteDataSource(get()) }
    }
}
