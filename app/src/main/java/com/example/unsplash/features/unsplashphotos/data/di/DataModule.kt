package com.example.unsplash.features.unsplashphotos.data.di

import com.example.unsplash.features.unsplashphotos.data.datasource.api.UnsplashPhotoNetworkDataSource
import com.example.unsplash.features.unsplashphotos.data.datasource.api.retrofit.UnsplashPhotoApiService
import com.example.unsplash.features.unsplashphotos.data.repository.UnsplashPhotoRepositoryImpl
import com.example.unsplash.features.unsplashphotos.domain.UnsplashPhotoRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val URL_BASE = "https://api.unsplash.com"

val retrofitModule = module {
    single { provideRetrofitInstance() }
}

private fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
    .baseUrl(URL_BASE)
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

val unsplashPhotoApiModule = module {
    factory { provideUnsplashPhotoApiService(retrofit = get()) }
    factory { UnsplashPhotoNetworkDataSource(unsplashPhotoApiService = get()) }
    single {
        UnsplashPhotoRepositoryImpl(
            unsplashPhotoNetworkDataSource = get()
        ) as UnsplashPhotoRepository
    }
}

private fun provideUnsplashPhotoApiService(retrofit: Retrofit): UnsplashPhotoApiService =
    retrofit.create(UnsplashPhotoApiService::class.java)