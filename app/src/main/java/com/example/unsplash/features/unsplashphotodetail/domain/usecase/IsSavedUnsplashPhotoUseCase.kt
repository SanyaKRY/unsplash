package com.example.unsplash.features.unsplashphotodetail.domain.usecase

import com.example.unsplash.core.datatype.Result
import com.example.unsplash.core.datatype.ResultType
import com.example.unsplash.features.unsplashphotodetail.domain.UnsplashPhotoDetailRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import io.reactivex.rxjava3.core.Maybe

class IsSavedUnsplashPhotoUseCase(private val repository: UnsplashPhotoDetailRepository) {

    fun execute(unsplashPhoto: UnsplashPhotoDetailDomain): Maybe<Result<Boolean>> {
        return repository.searchUnsplashPhoto(unsplashPhoto).map {
            if (it.resultType == ResultType.SUCCESS) {
                Result.success(true)
            } else {
                Result.error(it.error)
            }
        }
    }
}