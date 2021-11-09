package com.mzukic.superhero

import android.app.Application
import com.mzukic.superhero.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Koin Android logger
            androidLogger(Level.NONE)
            //inject Android context
            androidContext(this@App)
            // use modules
            modules(appModule)
        }
    }
}
