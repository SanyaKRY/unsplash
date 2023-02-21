package com.example.unsplash.features.somefeature.domain.usecase

import com.example.unsplash.features.somefeature.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain

class InsertUnsplashPhotoDatabaseUseCase(private val repository: UnsplashPhotosRepository) {

    suspend fun execute(unsplashPhoto: UnsplashPhotoDetailDomain) {
        return repository.insertUnsplashPhoto(unsplashPhoto)
    }
}