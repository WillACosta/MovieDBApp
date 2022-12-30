package com.will.moviedbapp

import android.app.Application
import com.will.moviedbapp.data.DataModule
import com.will.moviedbapp.domain.DomainModule
import com.will.moviedbapp.network.NetworkModule
import com.will.moviedbapp.ui.UiModule
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

        DataModule.load()
        DomainModule.load()
        NetworkModule.load()
        UiModule.load()
    }
}
