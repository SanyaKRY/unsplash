package com.example.unsplash.features.unsplashphotodetail.domain

import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain

interface UnsplashPhotoDetailRepository {

    suspend fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain)
    suspend fun deleteUnsplashPhotoByUnsplashPhotoId(unsplashPhoto: UnsplashPhotoDetailDomain)
    suspend fun searchUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): UnsplashPhotoDetailDomain?
}