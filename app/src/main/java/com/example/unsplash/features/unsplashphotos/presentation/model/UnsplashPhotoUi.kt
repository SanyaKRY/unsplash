package com.example.unsplash.features.unsplashphotos.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UnsplashPhotoUi(
    val id: String,
    val urlsRegular: String
): Parcelable
