package com.example.unsplash.features.unsplashphotodetail.presenter.vm

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.InsertUnsplashPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.IsSavedUnsplashPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailUiToDetailDomainMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.DeleteUnsplashPhotoByIdPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.UiToDetailUiMapper
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UnsplashPhotoDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val insertUnsplashPhotoUseCase: InsertUnsplashPhotoUseCase,
    private val deleteUnsplashPhotoByIdPhotoUseCase: DeleteUnsplashPhotoByIdPhotoUseCase,
    private val isSavedUnsplashPhotoUseCase: IsSavedUnsplashPhotoUseCase
) : ViewModel() {

    var unsplashPhoto: UnsplashPhotoDetailUi? = UiToDetailUiMapper.map(savedStateHandle.get<UnsplashPhotoUi>("unsplashPhoto"))

    private val isSavedUnsplashPhotoMutableFlow: MutableStateFlow<Result<Boolean>> = MutableStateFlow(Result.loading())
    val isSavedUnsplashPhotoFlow: Flow<Result<Boolean>>
        get() = isSavedUnsplashPhotoMutableFlow

    init {
        Log.d("UnsplashPhotoLog", "init UnsplashPhotoDetailViewModel unsplashPhoto ${unsplashPhoto!!.unsplashPhotoId}")
        isSavedUnsplashPhoto(unsplashPhoto!!)
    }

    fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailUi) {
        viewModelScope.launch {
            insertUnsplashPhotoUseCase.execute(DetailUiToDetailDomainMapper.map(unsplashPhoto))
        }
    }

    fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailUi) {
        viewModelScope.launch {
            deleteUnsplashPhotoByIdPhotoUseCase.execute(DetailUiToDetailDomainMapper.map(unsplashPhoto))
        }
    }

    private fun isSavedUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailUi) {
        viewModelScope.launch {
            var isSaved: Result<Boolean> = isSavedUnsplashPhotoUseCase.execute(DetailUiToDetailDomainMapper.map(unsplashPhoto))
            isSavedUnsplashPhotoMutableFlow.value = isSaved
        }
    }
}