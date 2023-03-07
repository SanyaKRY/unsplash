package com.example.unsplash.features.unsplashphotos.domain.usecase

import android.util.Log
import androidx.paging.PagingData
import com.example.unsplash.features.unsplashphotos.domain.UnsplashPhotoRepository
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain
import kotlinx.coroutines.flow.Flow

class GetListOfUnsplashPhotosUseCase(private val repository: UnsplashPhotoRepository) {

    fun execute(): Flow<PagingData<UnsplashPhotoDomain>> {
        var listOfUnsplashPhotos = repository.getListOfUnsplashPhotos()
        Log.d("PetProject ", "${listOfUnsplashPhotos}")
        return listOfUnsplashPhotos
    }
}