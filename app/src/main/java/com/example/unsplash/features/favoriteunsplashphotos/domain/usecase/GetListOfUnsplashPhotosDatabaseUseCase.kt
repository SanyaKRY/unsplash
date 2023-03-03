package com.example.unsplash.features.favoriteunsplashphotos.domain.usecase

import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import io.reactivex.rxjava3.core.Flowable

class GetListOfUnsplashPhotosDatabaseUseCase(private val repository: UnsplashPhotosRepository) {

    fun execute(): Flowable<List<UnsplashPhotoDetailDomain>> {
        return repository.getAllUnsplashPhotos()
    }
}