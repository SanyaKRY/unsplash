package com.example.unsplash.features.favoriteunsplashphotos.domain

import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface UnsplashPhotosRepository {

    fun getAllUnsplashPhotos(): Flowable<List<UnsplashPhotoDetailDomain>>
    fun deleteAllUnsplashPhoto(): Completable
    fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): Completable
    fun deleteUnsplashPhotoByUnsplashPhotoId(unsplashPhoto: UnsplashPhotoDetailDomain): Completable
    fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): Completable
    fun getAllUnsplashPhotosSortById(): Flowable<List<UnsplashPhotoDetailDomain>>
    fun searchUnsplashPhoto(searchQuery: String): Flowable<List<UnsplashPhotoDetailDomain>>
}