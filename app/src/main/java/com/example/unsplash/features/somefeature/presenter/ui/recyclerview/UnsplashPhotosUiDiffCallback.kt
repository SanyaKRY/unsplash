package com.example.unsplash.features.somefeature.presenter.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi

class UnsplashPhotosUiDiffCallback(
    private val oldUnsplashPhotos: List<UnsplashPhotoDetailUi>,
    private val newUnsplashPhotos: List<UnsplashPhotoDetailUi>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldUnsplashPhotos[oldItemPosition].id == newUnsplashPhotos[newItemPosition].id

    override fun getOldListSize(): Int = oldUnsplashPhotos.size

    override fun getNewListSize(): Int = newUnsplashPhotos.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldUnsplashPhotos[oldItemPosition] == newUnsplashPhotos[newItemPosition]
}