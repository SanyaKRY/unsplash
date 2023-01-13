package com.example.unsplash.features.unsplashphotos.domain

import com.example.unsplash.core.datatype.Result
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain

interface UnsplashPhotoRepository {

    suspend fun getListOfUnsplashPhotos(): Result<List<UnsplashPhotoDomain>?>
}