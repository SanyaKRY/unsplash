package com.example.unsplash.features.unsplashphotos.domain.model

data class UnsplashPhotoDomain(
    val id: String,
    val urlsRegular: String,
    val unsplashPhotoDetailsBundleModel: UnsplashPhotoDetailsBundleModel,
    val userDetailsBundleModel: UserDetailsBundleModel
)

data class UnsplashPhotoDetailsBundleModel(
    val likes: Int
)

data class UserDetailsBundleModel(
    val username: String,
    val userLinksDetailsBundleModel: UserLinksDetailsBundleModel
)

data class UserLinksDetailsBundleModel(
    val html: String
)
