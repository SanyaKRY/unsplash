package com.example.unsplash.features.unsplashphotodetail.domain.model

data class UnsplashPhotoDetailDomain(
    var id: Int,
    val unsplashPhotoId: String,
    val urlsRegular: String,
    var isSaved: Boolean?
)
