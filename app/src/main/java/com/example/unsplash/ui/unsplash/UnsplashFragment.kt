package com.example.unsplash.ui.unsplash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unsplash.databinding.FragmentUnsplashBinding
import com.example.unsplash.recyclerviewadapter.UnsplashRecyclerViewAdapter
import com.example.unsplash.viewmodel.UnsplashViewModel

class UnsplashFragment : Fragment() {

    private val unsplashViewModel: UnsplashViewModel by viewModels<UnsplashViewModel>()

    private var _binding: FragmentUnsplashBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUnsplashBinding.inflate(inflater, container, false)
        val view = binding.root

        val unsplashAdapter = UnsplashRecyclerViewAdapter()
        val recyclerview = binding.recyclerView
        recyclerview.apply {
            adapter = unsplashAdapter
            layoutManager = LinearLayoutManager(context)
        }

        unsplashViewModel.unsplashPhotoLiveData.observe(viewLifecycleOwner) {
            unsplashAdapter.setData(it)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}