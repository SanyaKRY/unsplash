package com.example.unsplash

import android.app.Application
import com.example.unsplash.features.unsplashphotos.data.di.retrofitModule
import com.example.unsplash.features.unsplashphotos.data.di.unsplashPhotoApiModule
import com.example.unsplash.features.di.featureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BaseProjectApplication)
            modules(
                listOf(
                    retrofitModule,
                    unsplashPhotoApiModule,
                    featureModule
                )
            )
        }
    }
}