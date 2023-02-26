package com.example.unsplash.features.somefeature.presenter.vm

import androidx.lifecycle.*
import com.example.unsplash.features.somefeature.domain.usecase.*
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailDomainListToDetailUiListMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailUiToDetailDomainMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.core.datatype.ResultType

class UnsplashPhotoDatabaseViewModel(
    private val getListOfUnsplashPhotosDatabaseUseCase: GetListOfUnsplashPhotosDatabaseUseCase,
    private val deleteAllUnsplashPhotoDatabaseUseCase: DeleteAllUnsplashPhotoDatabaseUseCase,
    private val deleteUnsplashPhotoDatabaseUseCase: DeleteUnsplashPhotoDatabaseUseCase,
    private val insertUnsplashPhotoDatabaseUseCase: InsertUnsplashPhotoDatabaseUseCase,
    private val getListOfUnsplashPhotosSortByIdDatabaseUseCase: GetListOfUnsplashPhotosSortByIdDatabaseUseCase,
    private val searchUnsplashPhotoDatabaseUseCase: SearchUnsplashPhotoDatabaseUseCase,
) : ViewModel() {

    val getUnsplashPhotosDatabase: LiveData<Result<List<UnsplashPhotoDetailUi>>> =
        getListOfUnsplashPhotosDatabaseUseCase.execute().map { result: Result<List<UnsplashPhotoDetailDomain>> ->
            if (result.resultType == ResultType.SUCCESS) {
                Result.success(DetailDomainListToDetailUiListMapper.map(result.data))
            } else if (result.resultType == ResultType.ERROR) {
                Result.error(result.error)
            } else {
            Result.loading()
        }
    }

    val getUnsplashPhotosSortByIdDatabase: LiveData<Result<List<UnsplashPhotoDetailUi>>> =
        getListOfUnsplashPhotosSortByIdDatabaseUseCase.execute().map { result: Result<List<UnsplashPhotoDetailDomain>> ->
            if (result.resultType == ResultType.SUCCESS) {
                Result.success(DetailDomainListToDetailUiListMapper.map(result.data))
            } else if (result.resultType == ResultType.ERROR) {
                Result.error(result.error)
            } else {
                Result.loading()
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

    fun searchDatabase(searchQuery: String): LiveData<Result<List<UnsplashPhotoDetailUi>>> {
        var liveData: LiveData<Result<List<UnsplashPhotoDetailUi>>> =
            searchUnsplashPhotoDatabaseUseCase.execute(searchQuery).map { result: Result<List<UnsplashPhotoDetailDomain>> ->
            if (result.resultType == ResultType.SUCCESS) {
                Result.success(DetailDomainListToDetailUiListMapper.map(result.data))
            } else if (result.resultType == ResultType.ERROR) {
                Result.error(result.error)
            } else {
                Result.loading()
            }
        }
        return liveData
    }
}