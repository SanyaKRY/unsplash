package com.example.unsplash.features.favoriteunsplashphotos.presenter.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.core.datatype.ResultType
import com.example.unsplash.features.favoriteunsplashphotos.domain.usecase.*
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailDomainListToDetailUiListMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailUiToDetailDomainMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UnsplashPhotoDatabaseViewModel(
    private val getListOfUnsplashPhotosDatabaseUseCase: GetListOfUnsplashPhotosDatabaseUseCase,
    private val deleteAllUnsplashPhotoDatabaseUseCase: DeleteAllUnsplashPhotoDatabaseUseCase,
    private val deleteUnsplashPhotoDatabaseUseCase: DeleteUnsplashPhotoDatabaseUseCase,
    private val insertUnsplashPhotoDatabaseUseCase: InsertUnsplashPhotoDatabaseUseCase,
    private val getListOfUnsplashPhotosSortByIdDatabaseUseCase: GetListOfUnsplashPhotosSortByIdDatabaseUseCase,
    private val searchUnsplashPhotoDatabaseUseCase: SearchUnsplashPhotoDatabaseUseCase,
) : ViewModel() {

    private val searchUnsplashPhotoMutableFlow: MutableStateFlow<List<UnsplashPhotoDetailUi>> = MutableStateFlow(listOf())
    val searchUnsplashPhotoFlow: Flow<List<UnsplashPhotoDetailUi>>
        get() = searchUnsplashPhotoMutableFlow

    private val getUnsplashPhotosDatabaseMutableFlow: MutableStateFlow<Result<List<UnsplashPhotoDetailUi>>> = MutableStateFlow(Result.loading())
    val getUnsplashPhotosDatabaseUnsplashPhotoFlow: Flow<Result<List<UnsplashPhotoDetailUi>>>
        get() = getUnsplashPhotosDatabaseMutableFlow

    private val getUnsplashPhotosSortByIdDatabaseMutableFlow: MutableStateFlow<Result<List<UnsplashPhotoDetailUi>>> = MutableStateFlow(Result.loading())
    val getUnsplashPhotosSortByIdDatabaseFlow: Flow<Result<List<UnsplashPhotoDetailUi>>>
        get() = getUnsplashPhotosSortByIdDatabaseMutableFlow

    init {
        getUnsplashPhotosDatabase()
        getUnsplashPhotosSortByIdDatabase()
    }

    private fun getUnsplashPhotosSortByIdDatabase() {
        var flow: Flow<Result<List<UnsplashPhotoDetailDomain>>> = getListOfUnsplashPhotosSortByIdDatabaseUseCase.execute()
        viewModelScope.launch(Dispatchers.IO) {
            flow.collect { result: Result<List<UnsplashPhotoDetailDomain>> ->
                if (result.resultType == ResultType.SUCCESS) {
                    var list: List<UnsplashPhotoDetailUi> = DetailDomainListToDetailUiListMapper.map(result.data)
                    getUnsplashPhotosSortByIdDatabaseMutableFlow.value = Result.success(list)
                } else if (result.resultType == ResultType.ERROR) {
                    getUnsplashPhotosSortByIdDatabaseMutableFlow.value = Result.error(result.error)
                }
            }
        }
    }

    private fun getUnsplashPhotosDatabase() {
        var flow: Flow<Result<List<UnsplashPhotoDetailDomain>>> = getListOfUnsplashPhotosDatabaseUseCase.execute()
        viewModelScope.launch(Dispatchers.IO) {
            flow.collect { result: Result<List<UnsplashPhotoDetailDomain>> ->
                if (result.resultType == ResultType.SUCCESS) {
                    var list: List<UnsplashPhotoDetailUi> = DetailDomainListToDetailUiListMapper.map(result.data)
                    getUnsplashPhotosDatabaseMutableFlow.value = Result.success(list)
                } else if (result.resultType == ResultType.ERROR) {
                    getUnsplashPhotosDatabaseMutableFlow.value = Result.error(result.error)
                }
            }
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

    fun searchDatabase(searchQuery: String) {
        Log.d("PetProjectSearch", "searchDatabase $searchQuery")
        var flow: Flow<Result<List<UnsplashPhotoDetailDomain>>> = searchUnsplashPhotoDatabaseUseCase.execute(searchQuery)
        viewModelScope.launch(Dispatchers.IO) {
            flow.collect { result: Result<List<UnsplashPhotoDetailDomain>> ->
                if (result.resultType == ResultType.SUCCESS) {
                    var list: List<UnsplashPhotoDetailUi> = DetailDomainListToDetailUiListMapper.map(result.data)
                    Log.d("PetProjectSearch", "list.size: ${list.size}")
                    searchUnsplashPhotoMutableFlow.value = list
                } else if (result.resultType == ResultType.ERROR) {
                    // TODO
                }
            }
        }
    }
}