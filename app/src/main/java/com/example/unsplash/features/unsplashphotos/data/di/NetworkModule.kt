package com.example.unsplash.features.unsplashphotos.data.di

import com.example.unsplash.features.unsplashphotos.data.datasource.api.retrofit.UnsplashPhotoApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val URL_BASE = "https://api.unsplash.com"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideUnsplashPhotoApiService(retrofit: Retrofit): UnsplashPhotoApiService =
        retrofit.create(UnsplashPhotoApiService::class.java)
}