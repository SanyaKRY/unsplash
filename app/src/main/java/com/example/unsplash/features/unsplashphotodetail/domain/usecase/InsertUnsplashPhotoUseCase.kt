package com.example.unsplash.features.unsplashphotodetail.domain.usecase

import com.example.unsplash.features.unsplashphotodetail.domain.UnsplashPhotoDetailRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import io.reactivex.rxjava3.core.Completable

class InsertUnsplashPhotoUseCase(private val repository: UnsplashPhotoDetailRepository) {

    fun execute(unsplashPhoto: UnsplashPhotoDetailDomain): Completable {
        unsplashPhoto.isSaved = true
        return repository.insertUnsplashPhoto(unsplashPhoto)
    }
}