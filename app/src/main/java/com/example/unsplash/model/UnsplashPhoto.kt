package com.example.unsplash.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UnsplashPhoto(
    @Json(name = "id") val id: String,
    @Json(name = "urls") val urls: Urls,
    @Json(name = "likes") val likes: Int,
    @Json(name = "user") val user: User
)

@JsonClass(generateAdapter = true)
data class Urls(
    @Json(name = "regular") val regular: String
)

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "username") val username: String,
    @Json(name = "links") val links: Links
)

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "html") val html: String
)