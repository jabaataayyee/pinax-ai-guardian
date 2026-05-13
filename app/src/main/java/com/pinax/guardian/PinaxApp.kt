package com.pinax.guardian

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Pinax AI Guardian Application class
 * Initializes Hilt dependency injection and logging
 */
@HiltAndroidApp
class PinaxApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeLogging()
    }

    private fun initializeLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
