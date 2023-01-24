package com.example.unsplash.features.unsplashphotos.data.datasource.api

import com.example.unsplash.features.unsplashphotos.data.datasource.api.exceptions.handleNetworkExceptions
import com.example.unsplash.features.unsplashphotos.data.datasource.api.retrofit.UnsplashPhotoApiService
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.UnsplashPhotoApi
import java.lang.Exception

const val MAX_RESULTS_PER_PAGE: Int = 40

class UnsplashPhotoNetworkDataSource(private val unsplashPhotoApiService: UnsplashPhotoApiService) {

    suspend fun getListOfUnsplashPhotos(): Result<List<UnsplashPhotoApi>> {
        return try {
            val unsplashPhotos: List<UnsplashPhotoApi>? = unsplashPhotoApiService.getListOfUnsplashPhotos(
                MAX_RESULTS_PER_PAGE
            )
            Result.success(unsplashPhotos)
        } catch (ex: Exception) {
            Result.error(handleNetworkExceptions(ex))
        }
    }
}