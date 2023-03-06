package com.example.unsplash.features.di.repository

import com.example.unsplash.features.favoriteunsplashphotos.data.repository.UnsplashPhotosRepositoryImpl
import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.data.repository.UnsplashPhotoDetailRepositoryImpl
import com.example.unsplash.features.unsplashphotodetail.domain.UnsplashPhotoDetailRepository
import com.example.unsplash.features.unsplashphotos.data.repository.UnsplashPhotoRepositoryImpl
import com.example.unsplash.features.unsplashphotos.domain.UnsplashPhotoRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindUnsplashPhotoDetailRepository(
        unsplashPhotoDetailRepository: UnsplashPhotoDetailRepositoryImpl
    ): UnsplashPhotoDetailRepository

    @Binds
    @Singleton
    fun bindUnsplashPhotoRepository(
        unsplashPhotoRepository: UnsplashPhotoRepositoryImpl
    ): UnsplashPhotoRepository

    @Binds
    @Singleton
    fun bindUnsplashPhotosRepository(
        unsplashPhotosRepository: UnsplashPhotosRepositoryImpl
    ): UnsplashPhotosRepository
}