package com.example.unsplash

import android.app.Application
import com.example.unsplash.features.di.AppComponent
import com.example.unsplash.features.di.DaggerAppComponent

open class MainApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(context = applicationContext)
    }
}