package com.example.unsplash.features.somefeature.domain.usecase

import androidx.lifecycle.LiveData
import com.example.unsplash.features.somefeature.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain

class GetListOfUnsplashPhotosSortByIdDatabaseUseCase(private val repository: UnsplashPhotosRepository) {

    fun execute(): LiveData<List<UnsplashPhotoDetailDomain>> {
        return repository.getAllUnsplashPhotosSortById()
    }
}