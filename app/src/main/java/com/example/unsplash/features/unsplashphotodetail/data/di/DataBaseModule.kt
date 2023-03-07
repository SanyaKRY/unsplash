package com.example.unsplash.features.unsplashphotodetail.data.di

import android.content.Context
import androidx.room.Room
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.dao.UnsplashPhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): UnsplashPhotoDatabase {
        return Room.databaseBuilder(
            appContext,
            UnsplashPhotoDatabase::class.java,
            "unsplash_photo_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUnsplashPhotoDao(dataBase: UnsplashPhotoDatabase): UnsplashPhotoDao =
        dataBase.unsplashPhotoDao
}