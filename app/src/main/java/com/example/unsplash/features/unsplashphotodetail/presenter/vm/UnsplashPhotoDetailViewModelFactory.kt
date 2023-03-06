package com.example.unsplash.features.unsplashphotodetail.presenter.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.DeleteUnsplashPhotoByIdPhoyoUseCase
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.InsertUnsplashPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.IsSavedUnsplashPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class UnsplashPhotoDetailViewModelFactory @AssistedInject constructor(
    @Assisted("unsplashPhoto")private val unsplashPhoto: UnsplashPhotoDetailUi,
    private val insertUnsplashPhotoUseCase: InsertUnsplashPhotoUseCase,
    private val deleteUnsplashPhotoByidPhotoUseCase: DeleteUnsplashPhotoByIdPhoyoUseCase,
    private val isSavedUnsplashPhotoUseCase: IsSavedUnsplashPhotoUseCase
) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UnsplashPhotoDetailViewModel(
            unsplashPhoto,
            insertUnsplashPhotoUseCase,
            deleteUnsplashPhotoByidPhotoUseCase,
            isSavedUnsplashPhotoUseCase) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("unsplashPhoto") unsplashPhoto: UnsplashPhotoDetailUi): UnsplashPhotoDetailViewModelFactory
    }
}