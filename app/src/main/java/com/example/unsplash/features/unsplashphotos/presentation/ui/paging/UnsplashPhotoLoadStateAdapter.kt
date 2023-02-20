package com.example.unsplash.features.unsplashphotos.presentation.ui.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.databinding.LoadStateBinding

class UnsplashPhotoLoadStateAdapter : LoadStateAdapter<UnsplashPhotoLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val itemViewHolder = LoadStateBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        val viewHolder = LoadStateViewHolder(itemViewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    class LoadStateViewHolder(
        val binding: LoadStateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val progressBar = binding.progressBar
        private val errorMsg = binding.errorMessage
        private var loadState : LoadState? = null

        fun bindTo(loadState: LoadState) {
            this.loadState = loadState
            errorMsg.isVisible = loadState !is LoadState.Loading
            progressBar.isVisible = loadState is LoadState.Loading

            if (loadState is LoadState.Error) {
                errorMsg.text = loadState.error.localizedMessage
            }
        }
    }
}