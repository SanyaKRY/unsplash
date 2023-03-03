package com.example.unsplash.features.favoriteunsplashphotos.domain.usecase

import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import io.reactivex.rxjava3.core.Flowable

class SearchUnsplashPhotoDatabaseUseCase(private val repository: UnsplashPhotosRepository) {

    fun execute(searchQuery: String): Flowable<List<UnsplashPhotoDetailDomain>> {
        return repository.searchUnsplashPhoto(searchQuery)
    }
}