package com.will.moviedbapp.domain.di

import com.will.moviedbapp.domain.usecase.GetMovieUseCase
import com.will.moviedbapp.domain.usecase.GetTrendingMoviesUseCase
import com.will.moviedbapp.domain.usecase.SearchUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load() {
        return loadKoinModules(listOf(useCasesModule()))
    }

    private fun useCasesModule(): Module {
        return module {
            factory { GetMovieUseCase(get()) }
            factory { SearchUseCase(get()) }
            factory { GetTrendingMoviesUseCase(get()) }
        }
    }
}
