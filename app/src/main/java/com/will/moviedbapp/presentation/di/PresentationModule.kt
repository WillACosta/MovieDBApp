package com.will.moviedbapp.presentation.di

import com.will.moviedbapp.presentation.viewmodel.HomeViewModel
import com.will.moviedbapp.presentation.viewmodel.NameViewModel
import com.will.moviedbapp.presentation.viewmodel.PreferencesViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        return loadKoinModules(listOf(loadViewModels()))
    }

    private fun loadViewModels(): Module {
        return module {
            factory { HomeViewModel(get(), get()) }
            factory { NameViewModel(get()) }

            single { PreferencesViewModel(get()) }
        }
    }

}
