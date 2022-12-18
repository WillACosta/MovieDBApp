package com.will.moviedbapp.modules

import android.app.Application
import com.will.moviedbapp.modules.home.di.HomeModule
import com.will.moviedbapp.modules.shared.data.di.DataModule
import com.will.moviedbapp.modules.shared.di.SharedModule
import com.will.moviedbapp.modules.welcome.di.WelcomeModule
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
        DataModule.load()
        SharedModule.load()
        WelcomeModule.load()
    }
}
