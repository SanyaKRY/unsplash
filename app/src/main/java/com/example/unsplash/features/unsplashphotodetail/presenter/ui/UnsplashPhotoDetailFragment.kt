package com.example.unsplash.features.unsplashphotodetail.presenter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentUnsplashPhotoDetailBinding
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.UiToDetailUiMapper

class UnsplashPhotoDetailFragment : Fragment() {

    private var _binding: FragmentUnsplashPhotoDetailBinding? = null
    private val binding get() = _binding!!

    private val args: UnsplashPhotoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnsplashPhotoDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        bindViews()

        return view
    }

    private fun bindViews() {
        val unsplashPhotoDetailUi = UiToDetailUiMapper.map(args.unsplashPhoto)
        binding.apply {
            unsplashPhotoId.text = unsplashPhotoDetailUi.id
            loadImage(unsplashPhotoImageView, unsplashPhotoDetailUi.urlsRegular)
        }
    }

    private fun loadImage(imageView: AppCompatImageView, imageUri: String) {
        Glide.with(imageView.context)
            .load(imageUri)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
            .into(imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}