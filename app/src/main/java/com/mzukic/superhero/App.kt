package com.mzukic.superhero

import android.app.Application
import com.mzukic.superhero.di.appModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@App)
            // use modules
            modules(appModule)
        }
    }
}
