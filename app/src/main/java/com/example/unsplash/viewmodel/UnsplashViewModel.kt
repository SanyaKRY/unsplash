package com.example.unsplash.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash.api.UnsplashApiImpl
import com.example.unsplash.model.UnsplashPhoto
import kotlinx.coroutines.launch

class UnsplashViewModel : ViewModel() {

    private val _unsplashPhotoLiveData = MutableLiveData<List<UnsplashPhoto>>()
    val unsplashPhotoLiveData: LiveData<List<UnsplashPhoto>> = _unsplashPhotoLiveData

    init {
        viewModelScope.launch {
            val listOfUnsplashPhoto = UnsplashApiImpl.getListOfPhotos()
            Log.d("class UnsplashViewModel", listOfUnsplashPhoto.size.toString())
            _unsplashPhotoLiveData.value = listOfUnsplashPhoto
        }
    }
}