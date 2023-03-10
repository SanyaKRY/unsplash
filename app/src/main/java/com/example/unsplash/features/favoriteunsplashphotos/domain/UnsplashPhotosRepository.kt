package com.example.unsplash.features.favoriteunsplashphotos.domain

import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import kotlinx.coroutines.flow.Flow
import com.example.unsplash.core.datatype.Result

interface UnsplashPhotosRepository {

    fun getAllUnsplashPhotos(): Flow<Result<List<UnsplashPhotoDetailDomain>>>

    suspend fun deleteAllUnsplashPhoto()

    suspend fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain)

    suspend fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain)

    fun getAllUnsplashPhotosSortById(): Flow<Result<List<UnsplashPhotoDetailDomain>>>

    fun searchUnsplashPhoto(searchQuery: String): Flow<Result<List<UnsplashPhotoDetailDomain>>>
}