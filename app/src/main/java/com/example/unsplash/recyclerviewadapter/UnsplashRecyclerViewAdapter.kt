package com.example.unsplash.recyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.R
import com.example.unsplash.databinding.UnsplashItemBinding
import com.example.unsplash.model.UnsplashPhoto

class UnsplashRecyclerViewAdapter : RecyclerView.Adapter<UnsplashRecyclerViewAdapter.ViewHolder>() {

    private val listOfUnsplashPhoto = mutableListOf<UnsplashPhoto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(UnsplashItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unsplashItem = listOfUnsplashPhoto[position]
        holder.bind(unsplashItem)
    }

    override fun getItemCount(): Int {
        return listOfUnsplashPhoto.size
    }

    fun setData(newListOfUnsplashPhoto: List<UnsplashPhoto>) {
        listOfUnsplashPhoto.addAll(newListOfUnsplashPhoto)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: UnsplashItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(unsplashItem: UnsplashPhoto) {
            binding.unsplashphotoId.text = unsplashItem.id
        }
    }
}