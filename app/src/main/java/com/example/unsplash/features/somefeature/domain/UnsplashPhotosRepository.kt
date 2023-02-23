package com.example.unsplash.features.somefeature.domain

import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import kotlinx.coroutines.flow.Flow

interface UnsplashPhotosRepository {

    fun getAllUnsplashPhotos(): Flow<List<UnsplashPhotoDetailDomain>>

    suspend fun deleteAllUnsplashPhoto()

    suspend fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain)

    suspend fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain)

    fun getAllUnsplashPhotosSortById(): Flow<List<UnsplashPhotoDetailDomain>>

    fun searchUnsplashPhoto(searchQuery: String): Flow<List<UnsplashPhotoDetailDomain>>
}