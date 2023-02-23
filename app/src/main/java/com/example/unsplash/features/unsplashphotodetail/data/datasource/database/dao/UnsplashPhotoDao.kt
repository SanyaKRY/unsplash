package com.example.unsplash.features.unsplashphotodetail.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface UnsplashPhotoDao {

    @Insert
    suspend fun insert(unsplashPhoto: UnsplashPhotoDatabase)

    @Delete
    suspend fun delete(unsplashPhoto: UnsplashPhotoDatabase)

    @Query("DELETE FROM unsplash_photo_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM unsplash_photo_table WHERE unsplashPhotoId = :unsplashPhotoId")
    suspend fun search(unsplashPhotoId: String): UnsplashPhotoDatabase

    @Query("SELECT * FROM unsplash_photo_table WHERE unsplashPhotoId like :searchQuery")
    fun searchByQuery(searchQuery: String): Flow<List<UnsplashPhotoDatabase>>

    @Query("SELECT * FROM unsplash_photo_table")
    fun getAllUnsplashPhotos(): Flow<List<UnsplashPhotoDatabase>>

    @Query("SELECT * FROM unsplash_photo_table ORDER BY unsplashPhotoId ASC")
    fun getAllUnsplashPhotosSortById(): Flow<List<UnsplashPhotoDatabase>>
}