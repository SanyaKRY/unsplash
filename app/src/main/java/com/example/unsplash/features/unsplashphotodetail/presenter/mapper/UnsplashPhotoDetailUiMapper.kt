package com.example.unsplash.features.unsplashphotodetail.presenter.mapper

import com.example.unsplash.core.mapper.BaseMapper
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi

object UiToDetailUiMapper : BaseMapper<UnsplashPhotoUi, UnsplashPhotoDetailUi> {
    override fun map(type: UnsplashPhotoUi?): UnsplashPhotoDetailUi {
        return UnsplashPhotoDetailUi(
            id = type?.id ?: "",
            urlsRegular = type?.urlsRegular ?: "",
            isSaved = null
        )
    }
}

object DetailUiToDetailDomainMapper : BaseMapper<UnsplashPhotoDetailUi, UnsplashPhotoDetailDomain> {
    override fun map(type: UnsplashPhotoDetailUi?): UnsplashPhotoDetailDomain {
        return UnsplashPhotoDetailDomain(
            id = type?.id ?: "",
            urlsRegular = type?.urlsRegular ?: "",
            isSaved = type?.isSaved
        )
    }
}