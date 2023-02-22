package com.example.unsplash.features.unsplashphotodetail.presenter.vm

import android.util.Log
import androidx.lifecycle.*
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.DeleteUnsplashPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.InsertUnsplashPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.IsSavedUnsplashPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailUiToDetailDomainMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import kotlinx.coroutines.launch

class UnsplashPhotoDetailViewModel(
    private val unsplashPhoto: UnsplashPhotoDetailUi,
    private val insertUnsplashPhotoUseCase: InsertUnsplashPhotoUseCase,
    private val deleteUnsplashPhotoUseCase: DeleteUnsplashPhotoUseCase,
    private val isSavedUnsplashPhotoUseCase: IsSavedUnsplashPhotoUseCase
) : ViewModel() {

    private val isSavedUnsplashPhotoMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isSavedUnsplashPhotoLiveData: LiveData<Boolean>
        get() = isSavedUnsplashPhotoMutableLiveData

    private val isLoadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingLiveData: LiveData<Boolean>
        get() = isLoadingMutableLiveData

    init {
        Log.d("UnsplashPhotoLog", "init UnsplashPhotoDetailViewModel ${this.toString()}")
        Log.d("UnsplashPhotoLog", "init UnsplashPhotoDetailViewModel unsplashPhoto ${unsplashPhoto.unsplashPhotoId}")
        isSavedUnsplashPhoto(unsplashPhoto)
    }

    fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailUi) {
        viewModelScope.launch {
            insertUnsplashPhotoUseCase.execute(DetailUiToDetailDomainMapper.map(unsplashPhoto))
        }
    }

    fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailUi) {
        viewModelScope.launch {
            deleteUnsplashPhotoUseCase.execute(DetailUiToDetailDomainMapper.map(unsplashPhoto))
        }
    }

    private fun isSavedUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailUi) {
        isLoadingLiveData(true)
        viewModelScope.launch {
            var isSaved: Boolean = isSavedUnsplashPhotoUseCase.execute(DetailUiToDetailDomainMapper.map(unsplashPhoto))
            isSavedUnsplashPhotoMutableLiveData.value = isSaved
            isLoadingLiveData(false)
        }
    }

    private fun isLoadingLiveData(isLoading: Boolean) {
        this.isLoadingMutableLiveData.value = isLoading
    }
}