package com.example.unsplash.features.unsplashphotos.presentation.mapper

import com.example.unsplash.core.mapper.BaseMapper
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain
import com.example.unsplash.features.unsplashphotos.presentation.model.*

object DomainToUiMapper : BaseMapper<List<UnsplashPhotoDomain>, List<UnsplashPhotoUi>> {
    override fun map(type: List<UnsplashPhotoDomain>?): List<UnsplashPhotoUi> {
        return type?.map {
            UnsplashPhotoUi(
                unsplashPhotoId = it.unsplashPhotoId,
                urlsRegular = it.urlsRegular,
                UnsplashPhotoDetailsBundleModel(
                    id = it.unsplashPhotoId,
                    urlsRegular = it.urlsRegular,
                    likes = it.likes,
                    User(
                        username = it.user.username,
                        Links(html = it.user.links.html)
                    )
                )
            )
        } ?: emptyList()
    }
}

object PagingDomainToUiMapper : BaseMapper<UnsplashPhotoDomain, UnsplashPhotoUi> {
    override fun map(type: UnsplashPhotoDomain?): UnsplashPhotoUi {
        return UnsplashPhotoUi(
            unsplashPhotoId = type!!.unsplashPhotoId,
            urlsRegular = type.urlsRegular,
            unsplashPhotoDetailsBundleModel = UnsplashPhotoDetailsBundleModel(
                id = type.unsplashPhotoId,
                urlsRegular = type.urlsRegular,
                likes = type.likes,
                User(
                    username = type.user.username,
                    Links(html = type.user.links.html)
                )
            )
        )
    }
}
