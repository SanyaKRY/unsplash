package com.example.unsplash.features.unsplashphotodetail.domain.usecase

import com.example.unsplash.core.datatype.Result
import com.example.unsplash.features.unsplashphotodetail.domain.UnsplashPhotoDetailRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import javax.inject.Inject

class IsSavedUnsplashPhotoUseCase @Inject constructor(private val repository: UnsplashPhotoDetailRepository) {

    suspend fun execute(unsplashPhoto: UnsplashPhotoDetailDomain): Result<Boolean> {
        var result: UnsplashPhotoDetailDomain? = repository.searchUnsplashPhoto(unsplashPhoto)

        return if (result != null) {
            Result.success(data = true)
        } else {
            Result.success(data = false)
        }
    }
}