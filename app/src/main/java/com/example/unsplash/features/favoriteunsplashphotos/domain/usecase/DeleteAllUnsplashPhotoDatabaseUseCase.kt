package com.example.unsplash.features.favoriteunsplashphotos.domain.usecase

import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository

class DeleteAllUnsplashPhotoDatabaseUseCase(private val repository: UnsplashPhotosRepository) {

    suspend fun execute() {
        return repository.deleteAllUnsplashPhoto()
    }
}