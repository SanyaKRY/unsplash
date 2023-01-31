package com.example.unsplash.features.unsplashphotos.presentation.model

import android.os.Parcelable
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDetailsBundleModel
import com.example.unsplash.features.unsplashphotos.domain.model.UserDetailsBundleModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class UnsplashPhotoUi(
    val id: String,
    val urlsRegular: String,
    val unsplashPhotoDetailsBundleModel: @RawValue UnsplashPhotoDetailsBundleModel,
    val userDetailsBundleModel: @RawValue UserDetailsBundleModel
): Parcelable

@Parcelize
data class UnsplashPhotoDetailsBundleModel(
    val likes: Int
): Parcelable

@Parcelize
data class UserDetailsBundleModel(
    val username: String,
    val userLinksDetailsBundleModel: @RawValue UserLinksDetailsBundleModel
): Parcelable

@Parcelize
data class UserLinksDetailsBundleModel(
    val html: String
): Parcelable

