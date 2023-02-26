package com.example.unsplash.features.favoriteunsplashphotos.domain.usecase

import androidx.lifecycle.LiveData
import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import com.example.unsplash.core.datatype.Result

class SearchUnsplashPhotoDatabaseUseCase(private val repository: UnsplashPhotosRepository) {

    fun execute(searchQuery: String): LiveData<Result<List<UnsplashPhotoDetailDomain>>> {
        return repository.searchUnsplashPhoto(searchQuery)
    }
}