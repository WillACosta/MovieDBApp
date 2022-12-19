package com.will.moviedbapp.modules.settings

import com.will.moviedbapp.modules.settings.presentation.SettingsFragment
import com.will.moviedbapp.modules.settings.presentation.fragments.UpdateNameFragment
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object SettingsModule {
    fun load() {
        loadKoinModules(listOf(loadPresentation()))
    }

    private fun loadPresentation(): Module {
        return module {
            factory { UpdateNameFragment() }
            factory { SettingsFragment() }
        }
    }
}
