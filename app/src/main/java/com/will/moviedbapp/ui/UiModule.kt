package com.will.moviedbapp.ui

import com.will.moviedbapp.ui.fragments.*
import com.will.moviedbapp.ui.viewmodels.*
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object UiModule {
    fun load() {
        loadKoinModules(
            listOf(
                fragmentsModule(),
                viewModelsModule()
            )
        )
    }

    private fun fragmentsModule(): Module = module {
        factory { FeaturedMoviesFragment() }
        factory { GenreFragment() }
        factory { HomeFragment() }
        factory { SettingsFragment() }
        UpdateNameFragment()
    }
}

private fun viewModelsModule(): Module = module {
    factory { FeaturedMoviesViewModel(get()) }
    factory { GenreViewModel(get(), get()) }
    factory { HomeViewModel(get()) }
    factory { MovieDetailsViewModel(get()) }
    factory { NameViewModel(get()) }
    factory { PreferencesViewModel(get()) }
    factory { SettingsViewModel(get()) }
    factory { WelcomeViewModel(get()) }
}
