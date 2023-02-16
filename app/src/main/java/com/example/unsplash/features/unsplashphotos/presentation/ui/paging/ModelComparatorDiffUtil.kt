package com.example.unsplash.features.unsplashphotos.presentation.ui.paging

import androidx.recyclerview.widget.DiffUtil
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi

object ModelComparatorDiffUtil : DiffUtil.ItemCallback<UnsplashPhotoUi>() {

    override fun areItemsTheSame(
        oldItem: UnsplashPhotoUi,
        newItem: UnsplashPhotoUi
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: UnsplashPhotoUi,
        newItem: UnsplashPhotoUi
    ) = oldItem == newItem
}