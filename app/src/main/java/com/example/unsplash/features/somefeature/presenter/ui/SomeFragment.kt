package com.example.unsplash.features.somefeature.presenter.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplash.R
import com.example.unsplash.core.datatype.ResultType
import com.example.unsplash.databinding.FragmentSomeBinding
import com.example.unsplash.features.somefeature.presenter.ui.recyclerview.UnsplashPhotosUiAdapter
import com.example.unsplash.features.somefeature.presenter.vm.UnsplashPhotoDatabaseViewModel
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
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
        viewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner, Observer { list ->
            when (list.resultType) {
                ResultType.LOADING -> {
                    // TODO
                }
                ResultType.SUCCESS -> {
                    var listUnsplashPhoto: List<UnsplashPhotoDetailUi> = list.data!!
                    unsplashPhotosUiAdapter.updateAdapter(listUnsplashPhoto)
                }
                ResultType.ERROR -> {
                    // TODO
                }
            }
        })
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
        viewModel.getUnsplashPhotosSortByIdDatabase.observe(viewLifecycleOwner) { unsplashPhotos ->
            when (unsplashPhotos.resultType) {
                ResultType.LOADING -> {
                    // TODO
                }
                ResultType.SUCCESS -> {
                    var list: List<UnsplashPhotoDetailUi> = unsplashPhotos.data!!
                    unsplashPhotosUiAdapter.updateAdapter(list)
                }
                ResultType.ERROR -> {
                    // TODO
                }
            }

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
        viewModel.getUnsplashPhotosDatabase.observe(viewLifecycleOwner, Observer {
            if (it.resultType == ResultType.SUCCESS) {
                onUnsplashPhotoReceived(it.data!!)
            }
        })
    }

    private fun onUnsplashPhotoReceived(listOfUnsplashPhotos: List<UnsplashPhotoDetailUi>) {
        Log.d("PetProject", "listOfUnsplashPhotos.size ${listOfUnsplashPhotos.size}")
        unsplashPhotosUiAdapter.updateAdapter(listOfUnsplashPhotos)
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