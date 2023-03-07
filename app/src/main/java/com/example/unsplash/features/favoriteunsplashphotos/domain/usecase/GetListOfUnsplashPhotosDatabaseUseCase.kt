package com.example.unsplash.features.favoriteunsplashphotos.domain.usecase

import androidx.lifecycle.LiveData
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import javax.inject.Inject

class GetListOfUnsplashPhotosDatabaseUseCase @Inject constructor(private val repository: UnsplashPhotosRepository) {

    fun execute(): LiveData<Result<List<UnsplashPhotoDetailDomain>>> {
        return repository.getAllUnsplashPhotos()
    }
}