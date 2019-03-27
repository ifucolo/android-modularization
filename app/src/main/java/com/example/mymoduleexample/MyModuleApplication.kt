package com.example.mymoduleexample

import android.app.Application
import com.example.data.di.dataModules
import com.example.domain.di.domainModule
import com.example.mymoduleexample.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyModuleApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyModuleApplication)

            modules(domainModule + dataModules + listOf(presentationModule))
        }
    }
}