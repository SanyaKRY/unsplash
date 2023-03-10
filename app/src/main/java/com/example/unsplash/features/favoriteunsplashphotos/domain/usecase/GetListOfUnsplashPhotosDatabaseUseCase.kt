package com.example.unsplash.features.favoriteunsplashphotos.domain.usecase

import com.example.unsplash.core.datatype.Result
import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import kotlinx.coroutines.flow.Flow

class GetListOfUnsplashPhotosDatabaseUseCase(private val repository: UnsplashPhotosRepository) {

    fun execute(): Flow<Result<List<UnsplashPhotoDetailDomain>>> {
        return  repository.getAllUnsplashPhotos()
    }
}