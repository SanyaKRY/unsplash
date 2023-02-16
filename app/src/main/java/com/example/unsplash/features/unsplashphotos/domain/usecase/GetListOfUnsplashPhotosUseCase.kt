package com.example.unsplash.features.unsplashphotos.domain.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.unsplash.features.unsplashphotos.domain.UnsplashPhotoRepository
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain

class GetListOfUnsplashPhotosUseCase(private val repository: UnsplashPhotoRepository) {

    fun execute(): LiveData<PagingData<UnsplashPhotoDomain>> {
        var listOfUnsplashPhotos = repository.getListOfUnsplashPhotos()
        Log.d("PetProject ", "${listOfUnsplashPhotos.value}")
        return listOfUnsplashPhotos
    }
}