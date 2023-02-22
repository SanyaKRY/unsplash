package com.example.unsplash.features.unsplashphotodetail.presenter.model

data class UnsplashPhotoDetailUi(
    var id: Int,
    val unsplashPhotoId: String,
    val urlsRegular: String,
    var isSaved: Boolean?
)
