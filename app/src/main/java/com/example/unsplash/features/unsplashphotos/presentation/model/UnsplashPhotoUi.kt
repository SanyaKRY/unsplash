package com.example.unsplash.features.unsplashphotos.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class UnsplashPhotoUi(
    val unsplashPhotoId: String,
    val urlsRegular: String,
    val unsplashPhotoDetailsBundleModel: @RawValue UnsplashPhotoDetailsBundleModel
): Parcelable

@Parcelize
data class UnsplashPhotoDetailsBundleModel(
    val id: String,
    val urlsRegular: String,
    val likes: Int,
    val user: User
): Parcelable

@Parcelize
data class User(
    val username: String,
    val userLinksDetailsBundleModel: @RawValue Links
): Parcelable

@Parcelize
data class Links(
    val html: String
): Parcelable

