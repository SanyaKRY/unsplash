package com.example.unsplash.features.favoriteunsplashphotos.domain.usecase

import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import kotlinx.coroutines.flow.Flow
import com.example.unsplash.core.datatype.Result
import javax.inject.Inject

class SearchUnsplashPhotoDatabaseUseCase @Inject constructor(private val repository: UnsplashPhotosRepository) {

    fun execute(searchQuery: String): Flow<Result<List<UnsplashPhotoDetailDomain>>> {
        return repository.searchUnsplashPhoto(searchQuery)
    }
}