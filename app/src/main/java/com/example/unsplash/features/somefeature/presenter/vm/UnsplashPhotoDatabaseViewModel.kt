package com.example.unsplash.features.somefeature.presenter.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash.features.somefeature.domain.usecase.*
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailDomainListToDetailUiListMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailUiToDetailDomainMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class UnsplashPhotoDatabaseViewModel(
    private val getListOfUnsplashPhotosDatabaseUseCase: GetListOfUnsplashPhotosDatabaseUseCase,
    private val deleteAllUnsplashPhotoDatabaseUseCase: DeleteAllUnsplashPhotoDatabaseUseCase,
    private val deleteUnsplashPhotoDatabaseUseCase: DeleteUnsplashPhotoDatabaseUseCase,
    private val insertUnsplashPhotoDatabaseUseCase: InsertUnsplashPhotoDatabaseUseCase,
    private val getListOfUnsplashPhotosSortByIdDatabaseUseCase: GetListOfUnsplashPhotosSortByIdDatabaseUseCase,
    private val searchUnsplashPhotoDatabaseUseCase: SearchUnsplashPhotoDatabaseUseCase,
) : ViewModel() {

    val getUnsplashPhotosDatabase: Flow<List<UnsplashPhotoDetailUi>> = convertDomainToUi(getListOfUnsplashPhotosDatabaseUseCase.execute())

    val getUnsplashPhotosSortByIdDatabase: Flow<List<UnsplashPhotoDetailUi>> = convertDomainToUi(getListOfUnsplashPhotosSortByIdDatabaseUseCase.execute())

    private fun convertDomainToUi(unsplashPhotos: Flow<List<UnsplashPhotoDetailDomain>>): Flow<List<UnsplashPhotoDetailUi>> {
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

    fun searchDatabase(searchQuery: String): Flow<List<UnsplashPhotoDetailUi>> {
        return searchUnsplashPhotoDatabaseUseCase.execute(searchQuery)
            .map { list: List<UnsplashPhotoDetailDomain> ->
                DetailDomainListToDetailUiListMapper.map(list)
            }
    }
}