package com.example.unsplash.features.unsplashphotos.presentation.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi

class UnsplashPhotosDiffCallback(private val oldUnsplashPhotos: List<UnsplashPhotoUi>,
                                 private val newUnsplashPhotos: List<UnsplashPhotoUi>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldUnsplashPhotos[oldItemPosition].unsplashPhotoId == newUnsplashPhotos[newItemPosition].unsplashPhotoId

    override fun getOldListSize(): Int = oldUnsplashPhotos.size

    override fun getNewListSize(): Int = newUnsplashPhotos.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldUnsplashPhotos[oldItemPosition] == newUnsplashPhotos[newItemPosition]
}