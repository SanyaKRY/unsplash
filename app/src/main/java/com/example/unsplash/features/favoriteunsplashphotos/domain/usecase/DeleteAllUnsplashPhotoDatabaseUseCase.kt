package com.example.unsplash.features.favoriteunsplashphotos.domain.usecase

import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import javax.inject.Inject

class DeleteAllUnsplashPhotoDatabaseUseCase @Inject constructor(private val repository: UnsplashPhotosRepository) {

    suspend fun execute() {
        return repository.deleteAllUnsplashPhoto()
    }
}