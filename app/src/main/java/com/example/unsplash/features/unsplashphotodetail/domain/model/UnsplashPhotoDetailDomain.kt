package com.example.unsplash.features.unsplashphotodetail.domain.model

data class UnsplashPhotoDetailDomain(
    val id: String,
    val urlsRegular: String,
    var isSaved: Boolean?
)
