package com.example.unsplash.features.unsplashphotos.data.repository.mapper

import com.example.unsplash.core.mapper.BaseMapper
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.UnsplashPhotoApi
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain

object ApiToDomainMapper : BaseMapper<List<UnsplashPhotoApi>, List<UnsplashPhotoDomain>> {
    override fun map(type: List<UnsplashPhotoApi>?): List<UnsplashPhotoDomain> {
        return type?.map {
            UnsplashPhotoDomain(
                id = it.id,
                urlsRegular = it.urls.regular
            )
        } ?: emptyList()
    }
}