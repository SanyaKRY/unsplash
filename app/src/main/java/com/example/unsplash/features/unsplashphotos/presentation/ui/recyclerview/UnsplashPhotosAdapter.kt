package com.example.unsplash.features.unsplashphotos.presentation.ui.recyclerview

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.databinding.UnsplashPhotoItemBinding
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi

class UnsplashPhotosAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val listOfUnsplashPhotos = mutableListOf<UnsplashPhotoUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewHolder = UnsplashPhotoItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)

        avoidMultipleClicks(itemViewHolder.root)

        return ViewHolder(itemViewHolder)
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