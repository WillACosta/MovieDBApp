package com.will.moviedbapp.presentation.di

import com.will.moviedbapp.presentation.view.home.HomeFragment
import com.will.moviedbapp.presentation.view.home.HomeViewModel
import com.will.moviedbapp.presentation.view.movie_details.MovieDetailsViewModel
import com.will.moviedbapp.presentation.view.name.NameViewModel
import com.will.moviedbapp.presentation.view.settings.SettingsFragment
import com.will.moviedbapp.presentation.view.shared.PreferencesViewModel
import com.will.moviedbapp.presentation.view.welcome.WelcomeViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        return loadKoinModules(listOf(loadViewModels(), loadFragments()))
    }

    private fun loadViewModels(): Module {
        return module {
            factory { HomeViewModel(get(), get()) }
            factory { NameViewModel(get()) }
            factory { WelcomeViewModel(get()) }
            factory { MovieDetailsViewModel(get()) }

            factory { PreferencesViewModel(get()) }
        }
    }

    private fun loadFragments(): Module {
        return module {
            factory { HomeFragment() }
            factory { SettingsFragment() }
        }
    }

}
