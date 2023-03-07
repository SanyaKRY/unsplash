package com.example.unsplash.features.unsplashphotos.presentation.ui.di

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi
import com.example.unsplash.features.unsplashphotos.presentation.ui.paging.UnsplashPhotoPagingAdapter
import dagger.assisted.AssistedFactory

@AssistedFactory
interface AdapterModule {

    fun createUnsplashPhotoPagingAdapter(
        unsplashPhotoDetailListener: (unsplashPhotoUi: UnsplashPhotoUi, AppCompatImageView, TextView) -> Unit,
        unsplashPhotoAndUserDetailsListener: (unsplashPhotoUi: UnsplashPhotoUi) -> Unit
    ): UnsplashPhotoPagingAdapter
}