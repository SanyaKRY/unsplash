package com.example.unsplash.features.unsplashphotodetail.domain

import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface UnsplashPhotoDetailRepository {

    fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): Completable
    fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): Completable
    fun deleteUnsplashPhotoByUnsplashPhotoId(unsplashPhoto: UnsplashPhotoDetailDomain): Completable
    fun searchUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): Maybe<UnsplashPhotoDetailDomain>
}