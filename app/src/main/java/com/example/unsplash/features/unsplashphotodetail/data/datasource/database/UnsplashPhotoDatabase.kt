package com.example.unsplash.features.unsplashphotodetail.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.dao.UnsplashPhotoDao
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase

@Database(entities = arrayOf(UnsplashPhotoDatabase::class), version = 1, exportSchema = false)
abstract class UnsplashPhotoDatabase: RoomDatabase() {

    abstract val unsplashPhotoDao: UnsplashPhotoDao
}