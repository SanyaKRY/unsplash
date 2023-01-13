package com.example.unsplash.features.unsplashphotos.domain.usecase

import com.example.unsplash.core.datatype.Result
import com.example.unsplash.core.datatype.ResultType
import com.example.unsplash.features.unsplashphotos.domain.UnsplashPhotoRepository
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain

class GetListOfUnsplashPhotosUseCase(private val repository: UnsplashPhotoRepository) {

    suspend fun execute(): Result<List<UnsplashPhotoDomain>> {
        return repository.getListOfUnsplashPhotos().let { listOfUnsplashPhotos ->
            if (listOfUnsplashPhotos.resultType == ResultType.SUCCESS) {
                Result.success(listOfUnsplashPhotos.data)
            } else {
                Result.error(listOfUnsplashPhotos.error)
            }
        }
    }
}