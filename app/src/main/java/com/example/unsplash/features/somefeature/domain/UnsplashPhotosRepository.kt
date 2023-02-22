package com.example.unsplash.features.somefeature.domain

import androidx.lifecycle.LiveData
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain

interface UnsplashPhotosRepository {

    fun getAllUnsplashPhotos(): LiveData<List<UnsplashPhotoDetailDomain>>

    suspend fun deleteAllUnsplashPhoto()

    suspend fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain)

    suspend fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain)

    fun getAllUnsplashPhotosSortById(): LiveData<List<UnsplashPhotoDetailDomain>>

    fun searchUnsplashPhoto(searchQuery: String): LiveData<List<UnsplashPhotoDetailDomain>>
}