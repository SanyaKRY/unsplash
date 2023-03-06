package com.example.unsplash.features.favoriteunsplashphotos.domain.usecase

import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class DeleteUnsplashPhotoDatabaseUseCase @Inject constructor(private val repository: UnsplashPhotosRepository) {

    fun execute(unsplashPhoto: UnsplashPhotoDetailDomain): Completable {
        return repository.deleteUnsplashPhoto(unsplashPhoto)
    }
}