package com.example.unsplash.features.favoriteunsplashphotos.presenter.ui

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.MainApplication
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentFavoriteUnsplashPhotosBinding
import com.example.unsplash.features.favoriteunsplashphotos.presenter.ui.recyclerview.UnsplashPhotosUiAdapter
import com.example.unsplash.features.favoriteunsplashphotos.presenter.vm.UnsplashPhotoDatabaseViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class FavoriteUnsplashPhotosFragment : Fragment() {

    private var _binding: FragmentFavoriteUnsplashPhotosBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: UnsplashPhotoDatabaseViewModel

    private lateinit var recyclerView: RecyclerView

    lateinit var unsplashPhotosUiAdapter: UnsplashPhotosUiAdapter
        @Inject set

    override fun onAttach(context: Context) {
        (requireActivity().application as MainApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteUnsplashPhotosBinding.inflate(inflater, container, false)

        context?.let {
            binding.root.setBackgroundColor(ContextCompat.getColor(it, R.color.purple))
        }

        bindViews()
        setRecyclerView()
        observerLiveData()
        setItemTouchHelper()
        setHasOptionsMenu(true)

        val view = binding.root

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.unsplash_photo_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)

        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Log.d("PetProject", "onQueryTextSubmit")
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    runQuery(newText)
                }
                return true
            }
        })
    }

    fun runQuery(query: String) {
        val searchQuery = "%$query%"
        viewModel.searchDatabase(searchQuery)
        viewModel.searchUnsplashPhotoLiveData.observe(viewLifecycleOwner) { unsplashPhotos ->
            unsplashPhotosUiAdapter.updateAdapter(unsplashPhotos)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort_by_id -> getUnsplashPhotosSortById()
            R.id.action_delete_all -> deleteAllItems()
            R.id.action_sort -> getUnsplashPhotosSortById()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getUnsplashPhotosSortById() {
        viewModel.getUnsplashPhotosSortByIdDatabaseLiveData.observe(viewLifecycleOwner) { unsplashPhotos ->
            unsplashPhotosUiAdapter.updateAdapter(unsplashPhotos)
        }
    }

    private fun deleteAllItems() {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete All")
            .setMessage("Are you sure:?")
            .setPositiveButton("Yes") { dialog, _ ->
                viewModel.deleteAll()
                dialog.dismiss()
            }.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    private fun bindViews() {
        recyclerView = binding.recyclerView
    }

    private fun setRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = unsplashPhotosUiAdapter
        }
    }

    private fun observerLiveData() {
        viewModel.getUnsplashPhotosDatabaseUnsplashPhotoLiveData.observe(viewLifecycleOwner) { unsplashPhotos ->
            unsplashPhotosUiAdapter.updateAdapter(unsplashPhotos)
        }
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            binding.spinner.root.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun setItemTouchHelper() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val unsplashPhoto =
                    unsplashPhotosUiAdapter.getUnsplashPhotoByPosition(viewHolder.adapterPosition)
                viewModel.delete(unsplashPhoto)

                Snackbar.make(binding.root, "Deleted:!", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo") {
                        viewModel.insert(unsplashPhoto)
                    }
                    show()
                }
            }
        }).attachToRecyclerView(binding.recyclerView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}