package com.example.unsplash.features.unsplashphotos.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentUnsplashPhotosBinding
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi
import com.example.unsplash.features.unsplashphotos.presentation.ui.recyclerview.UnsplashPhotosAdapter
import com.example.unsplash.features.unsplashphotos.presentation.vm.UnsplashPhotoViewModel
import com.example.unsplash.features.unsplashphotos.utils.startAnimation
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UnsplashPhotosFragment : Fragment() {

    private var _binding: FragmentUnsplashPhotosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UnsplashPhotoViewModel by viewModel()

    private var recyclerView: RecyclerView? = null

    private val unsplashPhotoDetailListener: (
        unsplashPhotoUi: UnsplashPhotoUi, imageView: AppCompatImageView, textView: TextView
    ) -> Unit = {
            unsplashPhotoUi, imageView, textView ->
                val extras = FragmentNavigatorExtras(
                    imageView to unsplashPhotoUi.urlsRegular,
                    textView to unsplashPhotoUi.id
                )
                val action = UnsplashPhotosFragmentDirections
                    .actionUnsplashPhotosFragmentToUnsplashPhotoDetailFragment(unsplashPhotoUi)
                findNavController().navigate(action, extras)
    }

    private val unsplashPhotoAndUserDetailsListener: (
        unsplashPhotoUi: UnsplashPhotoUi
    ) -> Unit = {
            unsplashPhotoUi ->
        val action = UnsplashPhotosFragmentDirections
            .actionUnsplashPhotosFragmentToUnsplashPhotoAndUserDetailsFragment(unsplashPhotoUi)
        findNavController().navigate(action)
    }

    private val unsplashPhotosAdapter: UnsplashPhotosAdapter by inject{
        parametersOf(unsplashPhotoDetailListener, unsplashPhotoAndUserDetailsListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnsplashPhotosBinding.inflate(inflater, container, false)
        val view = binding.root
        bindViews()
        observerLiveData()

        return view
    }

    private fun bindViews() {
        recyclerView = binding.recyclerView
        bindFab()
    }

    private fun bindFab() {
        val animation = AnimationUtils.loadAnimation(context, R.anim.circle_explosion_anim).apply {
            duration = 700
            interpolator = AccelerateDecelerateInterpolator()
        }
        binding.floatingActionButton.setOnClickListener {
            binding.floatingActionButton.isVisible = false
            binding.circle.isVisible = true
            binding.circle.startAnimation(animation) {

                // display your fragment

                val action = UnsplashPhotosFragmentDirections
                    .actionUnsplashPhotosFragmentToSomeFragment()
                findNavController().navigate(action)

                context?.let {
                    binding.root.setBackgroundColor(ContextCompat.getColor(it, R.color.purple_200))
                }
                binding.circle.isVisible = false
            }
        }
    }

    private fun observerLiveData() {
        viewModel.unsplashPhotosLiveData.observe(viewLifecycleOwner, Observer(::onUnsplashPhotosReceived))
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, Observer(::onLoadingStateReceived))
    }

    private fun onLoadingStateReceived(isLoading: Boolean) {
        showSpinner(isLoading)
    }

    private fun showSpinner(isLoading: Boolean) {
        binding.spinner.apply {
            root.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun onUnsplashPhotosReceived(listOfUnsplashPhotos: List<UnsplashPhotoUi>) {
        showUnsplashPhotos(listOfUnsplashPhotos)
    }

    private fun showUnsplashPhotos(listOfUnsplashPhotos: List<UnsplashPhotoUi>) {
        populateRecyclerView(listOfUnsplashPhotos)
    }

    private fun populateRecyclerView(listOfUnsplashPhotos: List<UnsplashPhotoUi>) {
        recyclerView?.apply {
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = unsplashPhotosAdapter.apply { updateAdapter(listOfUnsplashPhotos) }
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}