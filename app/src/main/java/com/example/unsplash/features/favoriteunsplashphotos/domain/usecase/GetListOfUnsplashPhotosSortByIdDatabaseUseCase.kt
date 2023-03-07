package com.example.unsplash.features.favoriteunsplashphotos.domain.usecase

import androidx.lifecycle.LiveData
import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import com.example.unsplash.core.datatype.Result
import javax.inject.Inject

class GetListOfUnsplashPhotosSortByIdDatabaseUseCase @Inject constructor(private val repository: UnsplashPhotosRepository) {

    fun execute(): LiveData<Result<List<UnsplashPhotoDetailDomain>>> {
        return repository.getAllUnsplashPhotosSortById()
    }
}