package com.example.unsplash.features.favoriteunsplashphotos.presenter.ui.recyclerview

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplash.R
import com.example.unsplash.databinding.UnsplashPhotoItemBinding
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi

class ViewHolderDatabase(
    val binding: UnsplashPhotoItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(unsplashPhotoItem: UnsplashPhotoDetailUi) {
        binding.unsplashPhotoId.text = unsplashPhotoItem.unsplashPhotoId
        loadImage(binding.unsplashPhotoImage, unsplashPhotoItem.urlsRegular)

        setTransitionNames(binding.unsplashPhotoImage, binding.unsplashPhotoId, unsplashPhotoItem)
    }

    private fun setTransitionNames(imageView: AppCompatImageView, textView: TextView, unsplashPhotoItem: UnsplashPhotoDetailUi) {
        imageView.transitionName = unsplashPhotoItem.urlsRegular
        textView.transitionName = unsplashPhotoItem.unsplashPhotoId
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