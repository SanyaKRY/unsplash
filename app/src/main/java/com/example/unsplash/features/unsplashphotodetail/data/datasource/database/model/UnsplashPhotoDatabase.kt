package com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unsplash_photo_table")
data class UnsplashPhotoDatabase(
    @PrimaryKey
    val id: String,
    val urlsRegular: String,
    val isSaved: Boolean?
)
