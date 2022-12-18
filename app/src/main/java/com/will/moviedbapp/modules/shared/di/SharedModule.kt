package com.will.moviedbapp.modules.shared.di

import com.will.moviedbapp.modules.shared.presentation.PreferencesViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object SharedModule {
    fun load() {
        return loadKoinModules(listOf(loadViewModels()))
    }

    private fun loadViewModels(): Module {
        return module {
            factory { PreferencesViewModel(get()) }
        }
    }
}
