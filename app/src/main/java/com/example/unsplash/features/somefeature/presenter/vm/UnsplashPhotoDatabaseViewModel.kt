package com.example.unsplash.features.somefeature.presenter.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.unsplash.features.somefeature.domain.usecase.*
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailDomainListToDetailUiListMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailUiToDetailDomainMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UnsplashPhotoDatabaseViewModel(
    private val getListOfUnsplashPhotosDatabaseUseCase: GetListOfUnsplashPhotosDatabaseUseCase,
    private val deleteAllUnsplashPhotoDatabaseUseCase: DeleteAllUnsplashPhotoDatabaseUseCase,
    private val deleteUnsplashPhotoDatabaseUseCase: DeleteUnsplashPhotoDatabaseUseCase,
    private val insertUnsplashPhotoDatabaseUseCase: InsertUnsplashPhotoDatabaseUseCase,
    private val getListOfUnsplashPhotosSortByIdDatabaseUseCase: GetListOfUnsplashPhotosSortByIdDatabaseUseCase,
    private val searchUnsplashPhotoDatabaseUseCase: SearchUnsplashPhotoDatabaseUseCase,
) : ViewModel() {

    val getUnsplashPhotosDatabase: LiveData<List<UnsplashPhotoDetailUi>> =
        convertDomainToUi(getListOfUnsplashPhotosDatabaseUseCase.execute())
    val getUnsplashPhotosSortByIdDatabase: LiveData<List<UnsplashPhotoDetailUi>> =
        convertDomainToUi(getListOfUnsplashPhotosSortByIdDatabaseUseCase.execute())

    fun convertDomainToUi(unsplashPhotos: LiveData<List<UnsplashPhotoDetailDomain>>): LiveData<List<UnsplashPhotoDetailUi>> {
        return unsplashPhotos.map { list: List<UnsplashPhotoDetailDomain> ->
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

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) { deleteAllUnsplashPhotoDatabaseUseCase.execute() }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<UnsplashPhotoDetailUi>> {
        return searchUnsplashPhotoDatabaseUseCase.execute(searchQuery)
            .map { list: List<UnsplashPhotoDetailDomain> ->
                DetailDomainListToDetailUiListMapper.map(list)
            }
    }
}