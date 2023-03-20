package com.example.unsplash.features.unsplashphotos.data.datasource.api

import com.example.unsplash.features.unsplashphotos.data.datasource.api.retrofit.UnsplashPhotoApiService
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.UnsplashPhotoApi

class UnsplashPhotoNetworkDataSource(private val unsplashPhotoApiService: UnsplashPhotoApiService) {

    suspend fun getListOfUnsplashPhotos(currentPage: Int, perPage: Int): List<UnsplashPhotoApi>? {
        val unsplashPhotos = unsplashPhotoApiService.getListOfUnsplashPhotos(page = currentPage, perPage = perPage)
        return  unsplashPhotos
    }
}