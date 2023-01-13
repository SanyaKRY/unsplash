package com.example.unsplash.features.unsplashphotos.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.core.datatype.ResultType
import com.example.unsplash.features.unsplashphotos.domain.usecase.GetListOfUnsplashPhotosUseCase
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain
import com.example.unsplash.features.unsplashphotos.presentation.mapper.DomainToUiMapper
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UnsplashPhotoViewModel(
        private val getListOfUnsplashPhotosUseCase: GetListOfUnsplashPhotosUseCase
) : ViewModel() {

    private val unsplashPhotosMutableLiveData: MutableLiveData<List<UnsplashPhotoUi>> = MutableLiveData()
    val unsplashPhotosLiveData: LiveData<List<UnsplashPhotoUi>>
        get() = unsplashPhotosMutableLiveData

    private val areEmptyUnsplashPhotosMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val areEmptyUnsplashPhotosLiveData: LiveData<Boolean>
        get() = areEmptyUnsplashPhotosMutableLiveData

    private val isErrorMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isErrorLiveData: LiveData<Boolean>
        get() = isErrorMutableLiveData

    private val isLoadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingLiveData: LiveData<Boolean>
        get() = isLoadingMutableLiveData

    init {
        handleUnsplashPhotosLoad()
    }

    private fun handleUnsplashPhotosLoad() {
        isLoadingLiveData(true)
        viewModelScope.launch {
            val listOfUnsplashPhotosResult: Result<List<UnsplashPhotoDomain>> = getListOfUnsplashPhotosUseCase.execute()

            updateAppropriateLiveData(listOfUnsplashPhotosResult)
        }
    }

    private fun isLoadingLiveData(isLoading: Boolean) {
        this.isLoadingMutableLiveData.value = isLoading
    }

    private fun updateAppropriateLiveData(result: Result<List<UnsplashPhotoDomain>>) {
        if (isResultSuccess(result.resultType)) {
            onResultSuccess(result.data)
        } else {
            onResultError()
        }
    }

    private fun isResultSuccess(resultType: ResultType): Boolean {
        return resultType == ResultType.SUCCESS
    }

    private fun onResultSuccess(listOfUnsplashPhotos: List<UnsplashPhotoDomain>?) {
        val unsplashPhotos = DomainToUiMapper.map(listOfUnsplashPhotos)
        if (unsplashPhotos.isEmpty()) {
            areEmptyUnsplashPhotosMutableLiveData.value = true
        } else {
            unsplashPhotosMutableLiveData.value = unsplashPhotos
        }
        isLoadingLiveData(false)
    }

    private fun onResultError() {
        viewModelScope.launch {
            delay(300)
            isLoadingLiveData(false)
        }.invokeOnCompletion {
            isErrorMutableLiveData.value = true
        }
    }
}