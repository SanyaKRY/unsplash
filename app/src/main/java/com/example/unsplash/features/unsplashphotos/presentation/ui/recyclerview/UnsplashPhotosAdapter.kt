package com.example.unsplash.features.unsplashphotos.presentation.ui.recyclerview

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.databinding.UnsplashPhotoItemBinding
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi

class UnsplashPhotosAdapter(
    private val unsplashPhotoDetailListener: (
        unsplashPhotoUi: UnsplashPhotoUi, imageView: AppCompatImageView, textView: TextView
    ) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    private val listOfUnsplashPhotos = mutableListOf<UnsplashPhotoUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewHolder = UnsplashPhotoItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)

        val viewHolder = ViewHolder(itemViewHolder)


        setUnsplashPhotoItemListener(viewHolder)
        avoidMultipleClicks(itemViewHolder.root)


        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            bind(listOfUnsplashPhotos[position])
        }
    }

    override fun getItemCount(): Int {
        return listOfUnsplashPhotos.size
    }

    private fun avoidMultipleClicks(view: View) {
        view.isClickable = false
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            view.isClickable = true
        }, 1000)
    }

    private fun setUnsplashPhotoItemListener(viewHolder: ViewHolder) {
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                unsplashPhotoDetailListener.invoke(
                    listOfUnsplashPhotos[position],
                    viewHolder.binding.unsplashPhotoImage,
                    viewHolder.binding.unsplashPhotoId)
            }
        }
    }

    fun setData(unsplashPhotos: List<UnsplashPhotoUi>) {
        listOfUnsplashPhotos.clear()
        listOfUnsplashPhotos.addAll(unsplashPhotos)
    }

    fun updateAdapter(updatedList: List<UnsplashPhotoUi>) {
        val result = DiffUtil.calculateDiff(UnsplashPhotosDiffCallback(listOfUnsplashPhotos, updatedList))
        listOfUnsplashPhotos.clear()
        listOfUnsplashPhotos.addAll(updatedList)
        result.dispatchUpdatesTo(this)
    }
}