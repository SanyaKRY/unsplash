package com.example.unsplash.features.unsplashphotoanduserdetails.presenter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentUnsplashPhotoAndUserDetailsBinding
import com.example.unsplash.features.unsplashphotoanduserdetails.presenter.mapper.UiToUnsplashPhotoAndUserUiMapper
import com.example.unsplash.features.unsplashphotoanduserdetails.presenter.model.UnsplashPhotoAndUserDetailsUi
import com.example.unsplash.features.unsplashphotodetail.presenter.ui.UnsplashPhotoDetailFragmentArgs

class UnsplashPhotoAndUserDetailsFragment : Fragment() {

    private var _binding: FragmentUnsplashPhotoAndUserDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: UnsplashPhotoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnsplashPhotoAndUserDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        args.apply {
            val unsplashPhoto: UnsplashPhotoAndUserDetailsUi = UiToUnsplashPhotoAndUserUiMapper.map(unsplashPhoto.unsplashPhotoDetailsBundleModel)

            binding.unsplashPhotoId.text = getString(R.string.unsplash_photo_id, unsplashPhoto.id)
            binding.unsplashPhotoLikes.text = getString(R.string.unsplash_photo_likes, unsplashPhoto.likes.likes)
            binding.userUsername.text = getString(R.string.user_username, unsplashPhoto.user.username)
            binding.userLinksHtml.text = getString(R.string.user_links_html, unsplashPhoto.user.links.html)
            loadImage(binding.unsplashPhotoImageView, unsplashPhoto.urlsRegular)
        }

        return view
    }

    private fun loadImage(imageView: AppCompatImageView, imageUri: String) {
        Glide.with(imageView.context)
            .load(imageUri)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
            .override(400, 600)
            .into(imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}