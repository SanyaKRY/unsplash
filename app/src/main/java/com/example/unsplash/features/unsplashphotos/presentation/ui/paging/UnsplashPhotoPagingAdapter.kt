package com.example.unsplash.features.unsplashphotos.presentation.ui.paging

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.databinding.UnsplashPhotoItemBinding
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class UnsplashPhotoPagingAdapter @AssistedInject constructor (
    @Assisted private val unsplashPhotoDetailListener: (
        unsplashPhotoUi: UnsplashPhotoUi, imageView: AppCompatImageView, textView: TextView
    ) -> Unit,
    @Assisted private val unsplashPhotoAndUserDetailsListener: (unsplashPhotoUi: UnsplashPhotoUi) -> Unit
) : PagingDataAdapter<UnsplashPhotoUi, UnsplashPhotosViewHolder>(ModelComparatorDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashPhotosViewHolder {
        val itemViewHolder = UnsplashPhotoItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        val viewHolder = UnsplashPhotosViewHolder(itemViewHolder)

        setUnsplashPhotoItemListener(viewHolder)
        setItemListener(viewHolder)
        avoidMultipleClicks(itemViewHolder.root)

        return viewHolder
    }

    override fun onBindViewHolder(holder: UnsplashPhotosViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    private fun setItemListener(viewHolder: UnsplashPhotosViewHolder) {
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                getItem(position)?.let {
                    unsplashPhotoAndUserDetailsListener.invoke(it)
                }
            }
        }
    }

    private fun setUnsplashPhotoItemListener(viewHolder: UnsplashPhotosViewHolder) {
        viewHolder.binding.unsplashPhotoImage.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                getItem(position)?.let {
                    unsplashPhotoDetailListener.invoke(
                        it,
                        viewHolder.binding.unsplashPhotoImage,
                        viewHolder.binding.unsplashPhotoId)
                }
            }
        }
    }

    private fun avoidMultipleClicks(view: View) {
        view.isClickable = false
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            view.isClickable = true
        }, 1000)
    }
}