package com.example.unsplash.features.unsplashphotos.presentation.ui

import android.os.Bundle
import android.util.Log
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
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentUnsplashPhotosBinding
import com.example.unsplash.features.unsplashphotos.presentation.ui.paging.UnsplashPhotoPagingAdapter
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi
import com.example.unsplash.features.unsplashphotos.presentation.ui.paging.UnsplashPhotoLoadStateAdapter
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
    private val unsplashPhotoPagingAdapter: UnsplashPhotoPagingAdapter by inject{
        parametersOf(unsplashPhotoDetailListener, unsplashPhotoAndUserDetailsListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnsplashPhotosBinding.inflate(inflater, container, false)
        val view = binding.root

        bindViews()
        customizeRecyclerView()
        observerLiveData()
        setLoadStateListener()

        return view
    }

    private fun setLoadStateListener() {

        binding.recyclerView.adapter = unsplashPhotoPagingAdapter.withLoadStateFooter(
            footer = UnsplashPhotoLoadStateAdapter()
        )

        unsplashPhotoPagingAdapter.addLoadStateListener { loadState ->
            binding.apply {
                // Loading or NotLoading
                spinner.root.visibility = if (loadState.source.refresh is LoadState.Loading) View.VISIBLE else View.GONE
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading

                // error
                if (loadState.source.refresh is LoadState.Error) {
                    // TODO
                    Log.d("PetProject","Exception (: + ${loadState.source.refresh}")
                }

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    unsplashPhotoPagingAdapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    // TODO
                } else {
                    // TODO
                }
            }
        }
    }

    private fun bindViews() {
        bindFab()
        bindRecycler()
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
                val action = UnsplashPhotosFragmentDirections.actionUnsplashPhotosFragmentToSomeFragment()
                findNavController().navigate(action)
                context?.let {
                    binding.root.setBackgroundColor(ContextCompat.getColor(it, R.color.purple_200))
                }
                binding.circle.isVisible = false
            }
        }
    }

    private fun bindRecycler() {
        recyclerView = binding.recyclerView
    }

    private fun customizeRecyclerView() {
        recyclerView?.apply {
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
//            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            layoutManager = LinearLayoutManager(context)
            adapter = unsplashPhotoPagingAdapter
            setHasFixedSize(true)
        }
    }

    private fun observerLiveData() {
        viewModel.unsplashPhotos.observe(viewLifecycleOwner) {
            unsplashPhotoPagingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
