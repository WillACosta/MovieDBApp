package com.will.moviedbapp.modules.movie

import com.will.moviedbapp.modules.movie.domain.usecase.GetGenresList
import com.will.moviedbapp.modules.movie.domain.usecase.GetMovieUseCase
import com.will.moviedbapp.modules.movie.domain.usecase.GetTrendingMoviesUseCase
import com.will.moviedbapp.modules.movie.presentation.detail.MovieDetailsViewModel
import com.will.moviedbapp.modules.movie.presentation.featured.FeaturedMoviesFragment
import com.will.moviedbapp.modules.movie.presentation.featured.FeaturedMoviesViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object MovieModule {
    fun load() {
        loadKoinModules(listOf(loadPresentation(), loadDomain()))
    }

    private fun loadPresentation(): Module {
        return module {
            factory { FeaturedMoviesFragment() }
            factory { FeaturedMoviesViewModel(get()) }
            factory { MovieDetailsViewModel(get()) }
        }
    }

    private fun loadDomain(): Module {
        return module {
            factory { GetTrendingMoviesUseCase(get()) }
            factory { GetMovieUseCase(get()) }
            factory { GetGenresList() }
        }
    }
}
