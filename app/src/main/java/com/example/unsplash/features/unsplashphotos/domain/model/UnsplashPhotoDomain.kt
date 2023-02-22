package com.example.unsplash.features.unsplashphotos.domain.model

data class UnsplashPhotoDomain(
    val unsplashPhotoId: String,
    val urlsRegular: String,
    val likes: Int,
    val user: User
)

data class User(
    val username: String,
    val links: Links
)

data class Links(
    val html: String
)
