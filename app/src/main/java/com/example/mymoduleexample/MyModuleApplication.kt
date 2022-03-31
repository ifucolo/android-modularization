package com.example.mymoduleexample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyModuleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}