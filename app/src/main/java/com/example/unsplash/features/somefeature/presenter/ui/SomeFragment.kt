package com.example.unsplash.features.somefeature.presenter.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentSomeBinding
import com.example.unsplash.features.somefeature.presenter.ui.recyclerview.UnsplashPhotosUiAdapter
import com.example.unsplash.features.somefeature.presenter.vm.UnsplashPhotoDatabaseViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SomeFragment : Fragment() {

    private var _binding: FragmentSomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UnsplashPhotoDatabaseViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private val unsplashPhotosUiAdapter: UnsplashPhotosUiAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSomeBinding.inflate(inflater, container, false)

        context?.let {
            binding.root.setBackgroundColor(ContextCompat.getColor(it, R.color.purple))
        }

        recyclerView = binding.recyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = unsplashPhotosUiAdapter
        }

        viewModel.getUnsplashPhotosDatabase.observe(viewLifecycleOwner) {
            Log.d("PetProject", "getUnsplashPhotosDatabase.size ${it.size}")
            unsplashPhotosUiAdapter.updateAdapter(it)
        }

        binding.apply {
            binding.recyclerView.adapter = unsplashPhotosUiAdapter
        }


        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val unsplashPhoto = unsplashPhotosUiAdapter.getUnsplashPhotoByPosition(viewHolder.adapterPosition)
                viewModel.delete(unsplashPhoto)

                Snackbar.make(binding.root, "Deleted:!", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo"){
                        viewModel.insert(unsplashPhoto)
                    }
                    show()
                }
            }
        }).attachToRecyclerView(binding.recyclerView)


        val view = binding.root

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}