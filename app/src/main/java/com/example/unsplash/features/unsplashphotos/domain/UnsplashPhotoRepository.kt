package com.example.unsplash.features.unsplashphotos.domain

import androidx.paging.PagingData
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain
import kotlinx.coroutines.flow.Flow

interface UnsplashPhotoRepository {

    fun getListOfUnsplashPhotos(): Flow<PagingData<UnsplashPhotoDomain>>
}