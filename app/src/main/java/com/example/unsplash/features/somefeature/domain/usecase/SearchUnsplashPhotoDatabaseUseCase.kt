package com.example.unsplash.features.somefeature.domain.usecase

import androidx.lifecycle.LiveData
import com.example.unsplash.features.somefeature.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain

class SearchUnsplashPhotoDatabaseUseCase(private val repository: UnsplashPhotosRepository) {

    fun execute(searchQuery: String): LiveData<List<UnsplashPhotoDetailDomain>> {
        return repository.searchUnsplashPhoto(searchQuery)
    }
}