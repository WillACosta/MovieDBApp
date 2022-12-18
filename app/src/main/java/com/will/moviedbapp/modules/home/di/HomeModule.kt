package com.will.moviedbapp.modules.home.di

import com.will.moviedbapp.modules.home.domain.use_case.GetMovieUseCase
import com.will.moviedbapp.modules.home.domain.use_case.GetTrendingMoviesUseCase
import com.will.moviedbapp.modules.home.domain.use_case.SearchUseCase
import com.will.moviedbapp.modules.home.presentation.home.HomeFragment
import com.will.moviedbapp.modules.home.presentation.home.HomeViewModel
import com.will.moviedbapp.modules.home.presentation.movie_details.MovieDetailsViewModel
import com.will.moviedbapp.modules.home.presentation.settings.SettingsFragment
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object HomeModule {
    fun load() {
        return loadKoinModules(listOf(loadViewModels(), loadFragments(), loadUseCases()))
    }

    private fun loadViewModels(): Module {
        return module {
            factory { HomeViewModel(get(), get()) }
            factory { MovieDetailsViewModel(get()) }
        }
    }

    private fun loadFragments(): Module {
        return module {
            factory { HomeFragment() }
            factory { SettingsFragment() }
        }
    }

    private fun loadUseCases(): Module {
        return module {
            factory { GetMovieUseCase(get()) }
            factory { SearchUseCase(get()) }
            factory { GetTrendingMoviesUseCase(get()) }
        }
    }
}
