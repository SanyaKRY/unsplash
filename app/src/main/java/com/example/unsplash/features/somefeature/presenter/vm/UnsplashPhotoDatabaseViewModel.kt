package com.example.unsplash.features.somefeature.presenter.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.unsplash.features.somefeature.domain.usecase.DeleteUnsplashPhotoDatabaseUseCase
import com.example.unsplash.features.somefeature.domain.usecase.GetListOfUnsplashPhotosDatabaseUseCase
import com.example.unsplash.features.somefeature.domain.usecase.InsertUnsplashPhotoDatabaseUseCase
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailDomainListToDetailUiListMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailUiToDetailDomainMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UnsplashPhotoDatabaseViewModel(
    private val getListOfUnsplashPhotosDatabaseUseCase: GetListOfUnsplashPhotosDatabaseUseCase,
    private val deleteUnsplashPhotoDatabaseUseCase: DeleteUnsplashPhotoDatabaseUseCase,
    private val insertUnsplashPhotoDatabaseUseCase: InsertUnsplashPhotoDatabaseUseCase
) : ViewModel() {

    val getUnsplashPhotosDatabase: LiveData<List<UnsplashPhotoDetailUi>>

    init {
        getUnsplashPhotosDatabase =
            getListOfUnsplashPhotosDatabaseUseCase
                .execute()
                .map { list: List<UnsplashPhotoDetailDomain> ->
                    DetailDomainListToDetailUiListMapper.map(list)
                }
    }

    fun delete(unsplashPhoto: UnsplashPhotoDetailUi) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUnsplashPhotoDatabaseUseCase.execute(DetailUiToDetailDomainMapper.map(unsplashPhoto))
        }
    }

    fun insert(unsplashPhoto: UnsplashPhotoDetailUi) {
        viewModelScope.launch(Dispatchers.IO) {
            insertUnsplashPhotoDatabaseUseCase.execute(DetailUiToDetailDomainMapper.map(unsplashPhoto))
        }
    }
}