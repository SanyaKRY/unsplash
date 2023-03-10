package com.example.unsplash.features.unsplashphotos.data.repository.mapper

import com.example.unsplash.core.mapper.BaseMapper
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.UnsplashPhotoApi
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain
import com.example.unsplash.features.unsplashphotos.domain.model.User
import com.example.unsplash.features.unsplashphotos.domain.model.Links

object ApiToDomainMapper : BaseMapper<List<UnsplashPhotoApi>, List<UnsplashPhotoDomain>> {
    override fun map(type: List<UnsplashPhotoApi>?): List<UnsplashPhotoDomain> {
        return type?.map {
            UnsplashPhotoDomain(
                unsplashPhotoId = it.unsplashPhotoId,
                urlsRegular = it.urls.regular,
                likes = it.likes,
                User(
                    username = it.user.username,
                    Links(html = it.user.links.html)
                )
            )
        } ?: emptyList()
    }
}

object PagingApiToPagingDomainMapper : BaseMapper<UnsplashPhotoApi, UnsplashPhotoDomain> {
    override fun map(type: UnsplashPhotoApi?): UnsplashPhotoDomain {
        return UnsplashPhotoDomain(
            unsplashPhotoId = type!!.unsplashPhotoId,
                urlsRegular = type.urls.regular,
                likes = type.likes,
                User(
                    username = type.user.username,
                    Links(html = type.user.links.html)
                )
            )
    }
}