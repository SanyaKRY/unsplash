package com.example.unsplash.features.unsplashphotos.presentation.mapper

import com.example.unsplash.core.mapper.BaseMapper
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi

object DomainToUiMapper : BaseMapper<List<UnsplashPhotoDomain>, List<UnsplashPhotoUi>> {
    override fun map(type: List<UnsplashPhotoDomain>?): List<UnsplashPhotoUi> {
        return type?.map {
            UnsplashPhotoUi(
                id = it.id,
                urlsRegular = it.urlsRegular
            )
        } ?: emptyList()
    }
}