package com.app.aero.app

import android.app.Application
import com.app.aero.di.koinAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppController : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(this@AppController)
    }

    private fun initKoin(controller: AppController) {
        startKoin {
            androidContext(controller)
            modules(koinAppModule)
        }
    }
}