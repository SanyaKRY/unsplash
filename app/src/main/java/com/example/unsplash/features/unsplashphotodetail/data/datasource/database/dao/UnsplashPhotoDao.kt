package com.example.unsplash.features.unsplashphotodetail.data.datasource.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase

@Dao
interface UnsplashPhotoDao {

    @Insert
    suspend fun insert(unsplashPhoto: UnsplashPhotoDatabase)

    @Delete
    suspend fun delete(unsplashPhoto: UnsplashPhotoDatabase)

    @Query("DELETE FROM unsplash_photo_table WHERE unsplashPhotoId = :unsplashPhotoId")
    suspend fun deleteByUnsplashPhotoId(unsplashPhotoId: String)

    @Query("DELETE FROM unsplash_photo_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM unsplash_photo_table WHERE unsplashPhotoId = :unsplashPhotoId")
    suspend fun search(unsplashPhotoId: String): UnsplashPhotoDatabase

    @Query("SELECT * FROM unsplash_photo_table WHERE unsplashPhotoId like :searchQuery")
    fun searchByQuery(searchQuery: String): LiveData<List<UnsplashPhotoDatabase>>

    @Query("SELECT * FROM unsplash_photo_table")
    fun getAllUnsplashPhotos(): LiveData<List<UnsplashPhotoDatabase>>

    @Query("SELECT * FROM unsplash_photo_table ORDER BY unsplashPhotoId ASC")
    fun getAllUnsplashPhotosSortById(): LiveData<List<UnsplashPhotoDatabase>>
}