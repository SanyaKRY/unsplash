package com.example.unsplash.features.unsplashphotos.presentation.vm

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.unsplash.features.unsplashphotos.domain.usecase.GetListOfUnsplashPhotosUseCase
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain
import com.example.unsplash.features.unsplashphotos.presentation.mapper.PagingDomainToUiMapper
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class UnsplashPhotoViewModel @Inject constructor(
        private val getListOfUnsplashPhotosUseCase: GetListOfUnsplashPhotosUseCase
) : ViewModel() {

    val unsplashPhotos: Flow<PagingData<UnsplashPhotoUi>>

    init {
        unsplashPhotos = getListOfUnsplashPhotosUseCase.execute().map { pagingData: PagingData<UnsplashPhotoDomain> ->
            pagingData.map { unsplashPhotoDomain: UnsplashPhotoDomain ->
                PagingDomainToUiMapper.map(unsplashPhotoDomain)
            }
        }.cachedIn(viewModelScope)
    }
}