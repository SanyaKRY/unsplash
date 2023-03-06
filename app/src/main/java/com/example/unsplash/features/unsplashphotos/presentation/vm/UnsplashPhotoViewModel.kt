package com.example.unsplash.features.unsplashphotos.presentation.vm

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava2.cachedIn
import com.example.unsplash.features.unsplashphotos.domain.usecase.GetListOfUnsplashPhotosUseCase
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain
import com.example.unsplash.features.unsplashphotos.presentation.mapper.PagingDomainToUiMapper
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi
import io.reactivex.Observable
import javax.inject.Inject

class UnsplashPhotoViewModel @Inject constructor(
        private val getListOfUnsplashPhotosUseCase: GetListOfUnsplashPhotosUseCase
) : ViewModel() {

    val unsplashPhotos: Observable<PagingData<UnsplashPhotoUi>>

    init {
        unsplashPhotos = getListOfUnsplashPhotosUseCase.execute().map { pagingData: PagingData<UnsplashPhotoDomain> ->
            pagingData.map { unsplashPhotoDomain: UnsplashPhotoDomain ->
                PagingDomainToUiMapper.map(unsplashPhotoDomain)
            }
        }.cachedIn(viewModelScope)
    }
}