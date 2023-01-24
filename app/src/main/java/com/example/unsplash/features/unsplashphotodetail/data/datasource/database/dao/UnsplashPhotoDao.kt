package com.example.unsplash.features.unsplashphotodetail.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase

@Dao
    interface UnsplashPhotoDao {

    @Insert
    suspend fun insert(unsplashPhoto: UnsplashPhotoDatabase)

    @Delete
    suspend fun delete(unsplashPhoto: UnsplashPhotoDatabase)

    @Query("SELECT * FROM unsplash_photo_table WHERE id = :unsplashPhotoId")
    suspend fun search(unsplashPhotoId: String): UnsplashPhotoDatabase
}