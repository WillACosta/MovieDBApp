package com.will.moviedbapp.data

import com.will.moviedbapp.data.datasource.movie.CMovieDataSource
import com.will.moviedbapp.data.datasource.movie.MovieDataSource
import com.will.moviedbapp.data.repository.movie.CMovieRepository
import com.will.moviedbapp.data.repository.movie.MovieRepository
import com.will.moviedbapp.data.repository.UserPreferencesRepository
import com.will.moviedbapp.data.storage.CUserPreferencesRepository
import com.will.moviedbapp.data.utils.dataStore
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
        factory<UserPreferencesRepository> { CUserPreferencesRepository(get()) }
    }

    private fun dataSourcesModule(): Module = module {
        factory<MovieDataSource> { CMovieDataSource(get()) }
    }
}
