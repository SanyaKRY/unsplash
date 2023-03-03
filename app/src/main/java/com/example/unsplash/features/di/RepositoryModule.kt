package com.example.unsplash.features.di

import com.example.unsplash.features.favoriteunsplashphotos.data.repository.UnsplashPhotosRepositoryImpl
import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.data.repository.UnsplashPhotoDetailRepositoryImpl
import com.example.unsplash.features.unsplashphotodetail.domain.UnsplashPhotoDetailRepository
import com.example.unsplash.features.unsplashphotos.data.repository.UnsplashPhotoRepositoryImpl
import com.example.unsplash.features.unsplashphotos.domain.UnsplashPhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUnsplashPhotoDetailRepository(
        unsplashPhotoDetailRepositoryImpl: UnsplashPhotoDetailRepositoryImpl
    ): UnsplashPhotoDetailRepository

    @Binds
    @Singleton
    abstract fun bindUnsplashPhotoRepository(
        unsplashPhotoRepositoryImpl: UnsplashPhotoRepositoryImpl
    ): UnsplashPhotoRepository

    @Binds
    @Singleton
    abstract fun bindUnsplashPhotosRepository(
        unsplashPhotosRepositoryImpl: UnsplashPhotosRepositoryImpl
    ): UnsplashPhotosRepository
}