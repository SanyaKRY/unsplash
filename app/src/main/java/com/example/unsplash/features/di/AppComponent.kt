package com.example.unsplash.features.di

import android.content.Context
import com.example.unsplash.features.MainActivity
import com.example.unsplash.features.di.repository.RepositoryModule
import com.example.unsplash.features.favoriteunsplashphotos.presenter.ui.FavoriteUnsplashPhotosFragment
import com.example.unsplash.features.unsplashphotoanduserdetails.presenter.ui.UnsplashPhotoAndUserDetailsFragment
import com.example.unsplash.features.unsplashphotodetail.data.di.DatabaseModule
import com.example.unsplash.features.unsplashphotodetail.presenter.ui.UnsplashPhotoDetailFragment
import com.example.unsplash.features.unsplashphotos.data.di.NetworkModule
import com.example.unsplash.features.unsplashphotos.presentation.ui.UnsplashPhotosFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }

    fun inject(activity: MainActivity)

    fun inject(fragment: UnsplashPhotosFragment)

    fun inject(fragment: FavoriteUnsplashPhotosFragment)

    fun inject(fragment: UnsplashPhotoAndUserDetailsFragment)

    fun inject(fragment: UnsplashPhotoDetailFragment)
}