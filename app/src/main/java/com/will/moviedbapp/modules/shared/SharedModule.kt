package com.will.moviedbapp.modules.shared

import com.will.moviedbapp.modules.shared.data.DataModule
import com.will.moviedbapp.modules.shared.presentation.PreferencesViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object SharedModule {
    fun load() {
        loadKoinModules(listOf(loadViewModels()))
        DataModule.load()
    }

    private fun loadViewModels(): Module {
        return module {
            single { PreferencesViewModel(get()) }
        }
    }
}
