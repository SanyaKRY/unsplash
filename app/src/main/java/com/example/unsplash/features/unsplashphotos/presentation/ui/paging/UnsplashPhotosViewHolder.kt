package com.example.unsplash.features.unsplashphotos.presentation.ui.paging

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplash.R
import com.example.unsplash.databinding.UnsplashPhotoItemBinding
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.UnsplashPhotoApi
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi

class UnsplashPhotosViewHolder(val binding: UnsplashPhotoItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(unsplashPhotoItem: UnsplashPhotoUi) {
        binding.unsplashPhotoId.text = unsplashPhotoItem.id
        loadImage(binding.unsplashPhotoImage, unsplashPhotoItem.urlsRegular)

        setTransitionNames(binding.unsplashPhotoImage, binding.unsplashPhotoId, unsplashPhotoItem)
    }

    private fun setTransitionNames(imageView: AppCompatImageView, textView: TextView, unsplashPhotoItem: UnsplashPhotoUi) {
        imageView.transitionName = unsplashPhotoItem.urlsRegular
        textView.transitionName = unsplashPhotoItem.id
    }

    private fun loadImage(imageView: AppCompatImageView, imageUri: String) {
        Glide.with(imageView.context)
            .load(imageUri)    // источник изображения указан либо как путь к каталогу, URI или URL адреса
            .placeholder(R.drawable.ic_image)    // идентификатор локального ресурса приложения,
            // желательно использовать "drawable", то что будет показано пока не загрузится и отобразится нужное изображение.

            .error(R.drawable.ic_image)    // если завершится с ошибкой, тогда будет возвращен и показан ресурс drawable
            .override(200, 300)    // изменить размер изображения, прежде чем оно отобразится в ImageView
            .into(imageView)    // представление изображения, куда будет помещено настоящее изображение
    }
}