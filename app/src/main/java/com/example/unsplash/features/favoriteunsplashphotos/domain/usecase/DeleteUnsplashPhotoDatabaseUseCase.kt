package com.example.unsplash.features.favoriteunsplashphotos.domain.usecase

import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import javax.inject.Inject

class DeleteUnsplashPhotoDatabaseUseCase @Inject constructor(private val repository: UnsplashPhotosRepository) {

    suspend fun execute(unsplashPhoto: UnsplashPhotoDetailDomain) {
        return repository.deleteUnsplashPhoto(unsplashPhoto)
    }
}