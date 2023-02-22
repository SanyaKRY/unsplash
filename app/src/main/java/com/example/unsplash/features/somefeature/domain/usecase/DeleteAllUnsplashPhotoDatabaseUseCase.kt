package com.example.unsplash.features.somefeature.domain.usecase

import com.example.unsplash.features.somefeature.domain.UnsplashPhotosRepository

class DeleteAllUnsplashPhotoDatabaseUseCase(private val repository: UnsplashPhotosRepository) {

    suspend fun execute() {
        return repository.deleteAllUnsplashPhoto()
    }
}