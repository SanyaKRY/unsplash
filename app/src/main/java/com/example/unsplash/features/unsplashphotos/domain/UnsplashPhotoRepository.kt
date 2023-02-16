package com.example.unsplash.features.unsplashphotos.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain

interface UnsplashPhotoRepository {

    fun getListOfUnsplashPhotos(): LiveData<PagingData<UnsplashPhotoDomain>>
}