package com.will.moviedbapp.modules

import android.app.Application
import com.will.moviedbapp.modules.home.HomeModule
import com.will.moviedbapp.modules.movie.MovieModule
import com.will.moviedbapp.modules.settings.SettingsModule
import com.will.moviedbapp.modules.shared.SharedModule
import com.will.moviedbapp.modules.welcome.WelcomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class MovieDBApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieDBApp)
            fragmentFactory()
        }

        HomeModule.load()
        MovieModule.load()
        SettingsModule.load()
        WelcomeModule.load()
        SharedModule.load()
    }
}
