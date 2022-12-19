package com.will.moviedbapp.modules.welcome

import com.will.moviedbapp.modules.welcome.presentation.name.NameViewModel
import com.will.moviedbapp.modules.welcome.presentation.welcome.WelcomeViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object WelcomeModule {
    fun load() {
        return loadKoinModules(listOf(loadPresentation()))
    }

    private fun loadPresentation(): Module {
        return module {
            factory { NameViewModel(get()) }
            factory { WelcomeViewModel(get()) }
        }
    }
}
