package com.will.moviedbapp.domain

import com.will.moviedbapp.domain.usecases.*
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {
    fun load() {
        loadKoinModules(
            listOf(
                useCasesModule(),
            )
        )
    }

    private fun useCasesModule(): Module = module {
        factory { DiscoverMoviesUseCase(get()) }
        factory { GetGenresListUseCase(get()) }
        factory { GetMovieUseCase(get()) }
        factory { GetTrendingMoviesUseCase(get()) }
        factory { SearchUseCase(get()) }
    }

}
