package com.example.unsplash.features.unsplashphotos.domain.usecase

import android.util.Log
import androidx.paging.PagingData
import com.example.unsplash.features.unsplashphotos.domain.UnsplashPhotoRepository
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain
import io.reactivex.Observable

class GetListOfUnsplashPhotosUseCase(private val repository: UnsplashPhotoRepository) {

    fun execute(): Observable<PagingData<UnsplashPhotoDomain>> {
        var listOfUnsplashPhotos = repository.getListOfUnsplashPhotos()
        Log.d("PetProject ", "${listOfUnsplashPhotos}")
        return listOfUnsplashPhotos
    }
}