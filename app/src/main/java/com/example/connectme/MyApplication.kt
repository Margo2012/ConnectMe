package com.example.connectme

import android.app.Application
import com.example.connectme.di.databaseModule
import com.example.connectme.di.networkModule
import com.example.connectme.di.repositoryModule
import com.example.connectme.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(networkModule, databaseModule, repositoryModule, viewModelModule))
        }
    }
}