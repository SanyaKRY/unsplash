package com.example.unsplash.features.somefeature.domain.usecase

import com.example.unsplash.features.somefeature.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import kotlinx.coroutines.flow.Flow

class SearchUnsplashPhotoDatabaseUseCase(private val repository: UnsplashPhotosRepository) {

    fun execute(searchQuery: String): Flow<List<UnsplashPhotoDetailDomain>> {
        return repository.searchUnsplashPhoto(searchQuery)
    }
}