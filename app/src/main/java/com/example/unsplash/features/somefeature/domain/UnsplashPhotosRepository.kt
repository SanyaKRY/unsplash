package com.example.unsplash.features.somefeature.domain

import androidx.lifecycle.LiveData
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain

interface UnsplashPhotosRepository {

    fun getAllUnsplashPhotos(): LiveData<Result<List<UnsplashPhotoDetailDomain>>>

    suspend fun deleteAllUnsplashPhoto()

    suspend fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain)

    suspend fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain)

    fun getAllUnsplashPhotosSortById(): LiveData<Result<List<UnsplashPhotoDetailDomain>>>

    fun searchUnsplashPhoto(searchQuery: String): LiveData<Result<List<UnsplashPhotoDetailDomain>>>
}