package com.example.unsplash.features.unsplashphotodetail.presenter.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentUnsplashPhotoDetailBinding
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.UiToDetailUiMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import com.example.unsplash.features.unsplashphotodetail.presenter.vm.UnsplashPhotoDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UnsplashPhotoDetailFragment : Fragment() {

    private val viewModel: UnsplashPhotoDetailViewModel by viewModel() {
        parametersOf(UiToDetailUiMapper.map(args.unsplashPhoto))
    }

    private var _binding: FragmentUnsplashPhotoDetailBinding? = null
    private val binding get() = _binding!!

    private val args: UnsplashPhotoDetailFragmentArgs by navArgs()

    private lateinit var unsplashPhoto: UnsplashPhotoDetailUi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnsplashPhotoDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        Log.d("UnsplashPhotoLog", "onCreateView UnsplashPhotoDetailFragment ${this.toString()}")

        observerLiveData()
        bindViews()

        return view
    }

    private fun observerLiveData() {
        viewModel.isSavedUnsplashPhotoLiveData.observe(viewLifecycleOwner, Observer(::isSavedUnsplashPhoto))
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, Observer(::onLoadingStateReceived))
    }

    private fun isSavedUnsplashPhoto(isSaved: Boolean) {
        unsplashPhoto.isSaved = isSaved
        enableInserAndDeleteButton(unsplashPhoto.isSaved!!)
    }

    private fun enableInserAndDeleteButton(boolean: Boolean) {
        if (boolean) {
            binding.delete.isEnabled = true
        } else {
            binding.insert.isEnabled = true
        }
    }

    private fun onLoadingStateReceived(isLoading: Boolean) {
        showSpinner(isLoading)
    }

    private fun showSpinner(isLoading: Boolean) {
        Log.d("UnsplashPhotoLog", "showSpinner UnsplashPhotoDetailFragment ${isLoading}")
        binding.spinner.apply {
            root.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun handleInsertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailUi) {
        viewModel.insertUnsplashPhoto(unsplashPhoto)
        binding.insert.isEnabled = false
        binding.delete.isEnabled = true
    }

    private fun handleDeleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailUi) {
        viewModel.deleteUnsplashPhoto(unsplashPhoto)
        binding.delete.isEnabled = false
        binding.insert.isEnabled = true
    }

    private fun bindViews() {
        val unsplashPhotoDetailUi = UiToDetailUiMapper.map(args.unsplashPhoto)
        unsplashPhoto = unsplashPhotoDetailUi
        binding.apply {
            unsplashPhotoId.text = unsplashPhotoDetailUi.id
            loadImage(unsplashPhotoImageView, unsplashPhotoDetailUi.urlsRegular)
        }
        binding.insert.setOnClickListener {
            handleInsertUnsplashPhoto(unsplashPhoto)
        }
        binding.delete.setOnClickListener {
            handleDeleteUnsplashPhoto(unsplashPhoto)
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