package com.example.rickapi

import android.app.Application
import com.example.rickapi.di.appModule
import com.example.rickapi.di.networkModule
import com.example.rickapi.di.repositoryModule
import com.example.rickapi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}