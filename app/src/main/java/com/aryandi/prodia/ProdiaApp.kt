package com.aryandi.prodia

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ProdiaApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}