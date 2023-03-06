package com.example.unsplash.features.unsplashphotodetail.data.di

import android.content.Context
import androidx.room.Room
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.dao.UnsplashPhotoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(appContext: Context): UnsplashPhotoDatabase {
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