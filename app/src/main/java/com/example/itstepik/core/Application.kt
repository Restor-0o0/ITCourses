package com.example.itstepik.core

import android.app.Application
import com.example.itstepik.di.ApiModule
import com.example.itstepik.di.NetworkModeule
import com.example.itstepik.di.ReposetoryMoule
import com.example.itstepik.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(
                ApiModule,
                ViewModelModule,
                NetworkModeule,
                ReposetoryMoule

            )
        }
    }
}