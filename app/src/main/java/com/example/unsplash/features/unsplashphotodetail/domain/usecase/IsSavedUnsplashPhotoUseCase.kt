package com.example.unsplash.features.unsplashphotodetail.domain.usecase

import com.example.unsplash.features.unsplashphotodetail.domain.UnsplashPhotoDetailRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import io.reactivex.rxjava3.core.Maybe
import javax.inject.Inject

class IsSavedUnsplashPhotoUseCase @Inject constructor(private val repository: UnsplashPhotoDetailRepository) {

    fun execute(unsplashPhoto: UnsplashPhotoDetailDomain): Maybe<Boolean> {
        return repository.searchUnsplashPhoto(unsplashPhoto).map { true }
    }
}