package com.untitled.unboxing

import android.app.Application
import com.untitled.unboxing.sharedpreferences.PreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UnboxingApplication : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceUtil(applicationContext)
    }
}