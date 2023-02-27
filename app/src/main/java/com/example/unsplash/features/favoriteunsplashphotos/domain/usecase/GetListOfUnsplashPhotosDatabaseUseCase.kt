package com.example.unsplash.features.favoriteunsplashphotos.domain.usecase

import com.example.unsplash.core.datatype.Result
import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import io.reactivex.rxjava3.core.Flowable

class GetListOfUnsplashPhotosDatabaseUseCase(private val repository: UnsplashPhotosRepository) {

    fun execute(): Flowable<Result<List<UnsplashPhotoDetailDomain>>> {
        return repository.getAllUnsplashPhotos()
    }
}