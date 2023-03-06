package com.example.unsplash.features.favoriteunsplashphotos.presenter.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.databinding.UnsplashPhotoItemBinding
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import javax.inject.Inject

class UnsplashPhotosUiAdapter @Inject constructor() : RecyclerView.Adapter<ViewHolderDatabase>() {

    private var listOfUnsplashPhotos: List<UnsplashPhotoDetailUi> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDatabase {
        val itemViewHolder = UnsplashPhotoItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        val viewHolder = ViewHolderDatabase(itemViewHolder)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolderDatabase, position: Int) {
        holder.apply {
            bind(listOfUnsplashPhotos[position])
        }
    }

    fun getUnsplashPhotoByPosition(position: Int): UnsplashPhotoDetailUi {
        return listOfUnsplashPhotos[position]
    }

    override fun getItemCount(): Int {
        return listOfUnsplashPhotos.size
    }

    fun setData(unsplashPhotos: List<UnsplashPhotoDetailUi>) {
        this.listOfUnsplashPhotos = unsplashPhotos
    }

    fun updateAdapter(updatedList: List<UnsplashPhotoDetailUi>) {
        val result = DiffUtil.calculateDiff(UnsplashPhotosUiDiffCallback(listOfUnsplashPhotos, updatedList))
        this.listOfUnsplashPhotos = updatedList.toMutableList()
        result.dispatchUpdatesTo(this)
    }
}