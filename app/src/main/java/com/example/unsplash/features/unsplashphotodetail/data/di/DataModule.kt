package com.example.unsplash.features.unsplashphotodetail.data.di

import android.app.Application
import androidx.room.Room
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDBDataSource
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.dao.UnsplashPhotoDao
import com.example.unsplash.features.unsplashphotodetail.data.repository.UnsplashPhotoDetailRepositoryImpl
import com.example.unsplash.features.unsplashphotodetail.domain.UnsplashPhotoDetailRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabaseInstance(androidApplication()) }
}

private fun provideDatabaseInstance(application: Application): UnsplashPhotoDatabase =
    Room.databaseBuilder(application, UnsplashPhotoDatabase::class.java, "unsplash_photo_database")
        .fallbackToDestructiveMigration()
        .build()

val unsplashPhotoDatabaseModule = module {
    factory { provideUnsplashPhotoDao(dataBase = get()) }
    factory { UnsplashPhotoDBDataSource(unsplashPhotoDao = get()) }

    single {
        UnsplashPhotoDetailRepositoryImpl(
            unsplashPhotoDBDataSource = get()
        ) as UnsplashPhotoDetailRepository
    }
}

private fun provideUnsplashPhotoDao(dataBase: UnsplashPhotoDatabase): UnsplashPhotoDao =
    dataBase.unsplashPhotoDao
