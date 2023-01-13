package com.example.unsplash.features.unsplashphotos.data.repository


import com.example.unsplash.features.unsplashphotos.data.datasource.api.UnsplashPhotoNetworkDataSource
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.core.datatype.ResultType
import com.example.unsplash.features.unsplashphotos.data.repository.mapper.ApiToDomainMapper
import com.example.unsplash.features.unsplashphotos.domain.UnsplashPhotoRepository
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain

class UnsplashPhotoRepositoryImpl(
    private val unsplashPhotoNetworkDataSource: UnsplashPhotoNetworkDataSource
) : UnsplashPhotoRepository {

    override suspend fun getListOfUnsplashPhotos(): Result<List<UnsplashPhotoDomain>> {
        return unsplashPhotoNetworkDataSource.getListOfUnsplashPhotos().let { listOfUnsplashPhotos ->
            if (listOfUnsplashPhotos.resultType == ResultType.SUCCESS) {
                Result.success(ApiToDomainMapper.map(listOfUnsplashPhotos.data))
            } else {
                Result.error(listOfUnsplashPhotos.error)
            }
        }
    }
}