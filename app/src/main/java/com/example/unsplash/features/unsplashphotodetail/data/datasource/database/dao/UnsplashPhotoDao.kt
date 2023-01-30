package com.example.unsplash.features.unsplashphotodetail.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase

@Dao
    interface UnsplashPhotoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(unsplashPhoto: UnsplashPhotoDatabase)

    @Delete
    suspend fun delete(unsplashPhoto: UnsplashPhotoDatabase)

    @Query("SELECT * FROM unsplash_photo_table WHERE id = :unsplashPhotoId")
    suspend fun search(unsplashPhotoId: String): UnsplashPhotoDatabase
}