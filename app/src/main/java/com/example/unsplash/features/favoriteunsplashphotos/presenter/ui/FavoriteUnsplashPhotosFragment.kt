package com.example.unsplash.features.favoriteunsplashphotos.presenter.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.R
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.core.datatype.ResultType
import com.example.unsplash.databinding.FragmentFavoriteUnsplashPhotosBinding
import com.example.unsplash.features.favoriteunsplashphotos.presenter.ui.recyclerview.UnsplashPhotosUiAdapter
import com.example.unsplash.features.favoriteunsplashphotos.presenter.vm.UnsplashPhotoDatabaseViewModel
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteUnsplashPhotosFragment : Fragment() {

    private var _binding: FragmentFavoriteUnsplashPhotosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UnsplashPhotoDatabaseViewModel by viewModel()

    private var recyclerView: RecyclerView? = null
    private val unsplashPhotosUiAdapter: UnsplashPhotosUiAdapter by inject()

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

        observerFlow()
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

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Log.d("PetProject","onQueryTextSubmit")
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
        lifecycleScope.launch {
            viewModel.searchUnsplashPhotoFlow.collect {
                unsplashPhotosUiAdapter.updateAdapter(it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_sort_by_id -> getUnsplashPhotosSortById()
            R.id.action_delete_all -> deleteAllItems()
            R.id.action_sort -> getUnsplashPhotosSortById()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getUnsplashPhotosSortById() {
        lifecycleScope.launch {
            viewModel.getUnsplashPhotosSortByIdDatabaseFlow.collect { result: Result<List<UnsplashPhotoDetailUi>> ->
                when (result.resultType) {
                    ResultType.LOADING -> {
                        // TODO
                    }
                    ResultType.SUCCESS -> {
                        var list: List<UnsplashPhotoDetailUi> = result.data!!
                        unsplashPhotosUiAdapter.updateAdapter(list)
                    }
                }
            }
        }
    }

    private fun deleteAllItems() {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete All")
            .setMessage("Are you sure:?")
            .setPositiveButton("Yes"){
                    dialog, _ -> viewModel.deleteAll()
                dialog.dismiss()
            }.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    private fun bindViews() {
        recyclerView = binding.recyclerView
    }

    private fun setRecyclerView() {
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)

            adapter = unsplashPhotosUiAdapter


        }
    }

    private fun observerFlow() {
        lifecycleScope.launch {
            viewModel.getUnsplashPhotosDatabaseUnsplashPhotoFlow.collect { result: Result<List<UnsplashPhotoDetailUi>> ->
                when (result.resultType) {
                    ResultType.LOADING -> {
                        // TODO
                    }
                    ResultType.SUCCESS -> {
                        var list: List<UnsplashPhotoDetailUi> = result.data!!
                        onUnsplashPhotoReceived(list)
                    }
                }
            }
        }
    }

    private fun onUnsplashPhotoReceived(listOfUnsplashPhotos: List<UnsplashPhotoDetailUi>) {
        Log.d("PetProject", "listOfUnsplashPhotos.size ${listOfUnsplashPhotos.size}")
        unsplashPhotosUiAdapter.updateAdapter(listOfUnsplashPhotos)
    }

    private fun setItemTouchHelper() {
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}