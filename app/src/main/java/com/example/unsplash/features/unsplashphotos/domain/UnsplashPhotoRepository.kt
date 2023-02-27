package com.example.unsplash.features.unsplashphotos.domain

import androidx.paging.PagingData
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain
import io.reactivex.Observable

interface UnsplashPhotoRepository {

    fun getListOfUnsplashPhotos(): Observable<PagingData<UnsplashPhotoDomain>>
}