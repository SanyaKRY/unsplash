package com.example.unsplash.features.unsplashphotos.data.datasource.api

import android.util.Log
import com.example.unsplash.features.unsplashphotos.data.datasource.api.retrofit.UnsplashPhotoApiService
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.UnsplashPhotoApi
import io.reactivex.Single

class UnsplashPhotoNetworkDataSource(private val unsplashPhotoApiService: UnsplashPhotoApiService) {

    fun getListOfUnsplashPhotos(currentPage: Int, perPage: Int): Single<List<UnsplashPhotoApi>?> {
        Log.d("PetProject", "class UnsplashPhotoNetworkDataSource, currentPage: $currentPage, perPage: $perPage")
        val unsplashPhotos = unsplashPhotoApiService.getListOfUnsplashPhotos(page = currentPage, perPage = perPage)
        Log.d("PetProject", "class UnsplashPhotoNetworkDataSource, unsplashPhoto!!.size: ${unsplashPhotos}")
        return  unsplashPhotos
    }
}