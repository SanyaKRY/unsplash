package com.example.unsplash.features.favoriteunsplashphotos.domain.usecase

import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import io.reactivex.rxjava3.core.Completable

class DeleteAllUnsplashPhotoDatabaseUseCase(private val repository: UnsplashPhotosRepository) {

    fun execute(): Completable {
        return repository.deleteAllUnsplashPhoto()
    }
}