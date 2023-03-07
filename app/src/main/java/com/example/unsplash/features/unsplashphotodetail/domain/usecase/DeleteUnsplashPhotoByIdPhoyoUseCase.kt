package com.example.unsplash.features.unsplashphotodetail.domain.usecase

import com.example.unsplash.features.unsplashphotodetail.domain.UnsplashPhotoDetailRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import javax.inject.Inject

class DeleteUnsplashPhotoByIdPhoyoUseCase @Inject constructor(private val repository: UnsplashPhotoDetailRepository) {

    suspend fun execute(unsplashPhoto: UnsplashPhotoDetailDomain) {
        unsplashPhoto.isSaved = false
        return repository.deleteUnsplashPhotoByUnsplashPhotoId(unsplashPhoto)
    }
}