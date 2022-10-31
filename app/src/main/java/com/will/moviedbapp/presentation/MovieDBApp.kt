package com.will.moviedbapp.presentation

import android.app.Application
import com.will.moviedbapp.data.di.DataModule
import com.will.moviedbapp.domain.di.DomainModule
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
    }
}
