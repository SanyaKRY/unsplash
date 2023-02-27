package com.example.unsplash.features.unsplashphotodetail.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface UnsplashPhotoDao {

    @Insert
    fun insert(unsplashPhoto: UnsplashPhotoDatabase): Completable

    @Delete
    fun delete(unsplashPhoto: UnsplashPhotoDatabase): Completable

    @Query("DELETE FROM unsplash_photo_table WHERE unsplashPhotoId = :unsplashPhotoId")
    fun deleteByUnsplashPhotoId(unsplashPhotoId: String): Completable

    @Query("DELETE FROM unsplash_photo_table")
    fun deleteAll(): Completable

    @Query("SELECT * FROM unsplash_photo_table WHERE unsplashPhotoId = :unsplashPhotoId")
    fun search(unsplashPhotoId: String): Maybe<UnsplashPhotoDatabase>

    @Query("SELECT * FROM unsplash_photo_table WHERE unsplashPhotoId like :searchQuery")
    fun searchByQuery(searchQuery: String): Flowable<List<UnsplashPhotoDatabase>>

    @Query("SELECT * FROM unsplash_photo_table")
    fun getAllUnsplashPhotos(): Flowable<List<UnsplashPhotoDatabase>>

    @Query("SELECT * FROM unsplash_photo_table ORDER BY unsplashPhotoId ASC")
    fun getAllUnsplashPhotosSortById(): Flowable<List<UnsplashPhotoDatabase>>
}