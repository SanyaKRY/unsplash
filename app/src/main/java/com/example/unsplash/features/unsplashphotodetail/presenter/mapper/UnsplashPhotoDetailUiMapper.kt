package com.example.unsplash.features.unsplashphotodetail.presenter.mapper

import com.example.unsplash.core.mapper.BaseMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi

object UiToDetailUiMapper : BaseMapper<UnsplashPhotoUi, UnsplashPhotoDetailUi> {
    override fun map(type: UnsplashPhotoUi?): UnsplashPhotoDetailUi {
        return UnsplashPhotoDetailUi(
            id = type?.id ?: "",
            urlsRegular = type?.urlsRegular ?: ""
        )
    }
}