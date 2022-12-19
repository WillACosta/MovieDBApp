package com.will.moviedbapp.modules.home

import com.will.moviedbapp.modules.home.domain.usecase.SearchUseCase
import com.will.moviedbapp.modules.home.presentation.fragments.HomeFragment
import com.will.moviedbapp.modules.home.presentation.fragments.HomeViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object HomeModule {
    fun load() {
        loadKoinModules(listOf(loadPresentation(), loadDomain()))
    }

    private fun loadPresentation(): Module {
        return module {
            factory { HomeViewModel(get()) }
            factory { HomeFragment() }
        }
    }


    private fun loadDomain(): Module {
        return module {
            factory { SearchUseCase(get()) }
        }
    }
}
