package com.example.unsplash.features.unsplashphotoanduserdetails.presenter.model

data class UnsplashPhotoAndUserDetailsUi(
    val id: String,
    val urlsRegular: String,
    val likes: Likes,
    val user: User
)

data class Likes(
    val likes: Int
)

data class User(
    val username: String,
    val links: Links
)

data class Links(
    val html: String
)
