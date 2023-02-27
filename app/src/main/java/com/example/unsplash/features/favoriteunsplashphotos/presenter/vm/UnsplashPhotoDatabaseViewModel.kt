package com.example.unsplash.features.favoriteunsplashphotos.presenter.vm

import android.util.Log
import androidx.lifecycle.*
import com.example.unsplash.features.favoriteunsplashphotos.domain.usecase.*
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailDomainListToDetailUiListMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailUiToDetailDomainMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import com.example.unsplash.core.datatype.Result
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UnsplashPhotoDatabaseViewModel(
    private val getListOfUnsplashPhotosDatabaseUseCase: GetListOfUnsplashPhotosDatabaseUseCase,
    private val deleteAllUnsplashPhotoDatabaseUseCase: DeleteAllUnsplashPhotoDatabaseUseCase,
    private val deleteUnsplashPhotoDatabaseUseCase: DeleteUnsplashPhotoDatabaseUseCase,
    private val insertUnsplashPhotoDatabaseUseCase: InsertUnsplashPhotoDatabaseUseCase,
    private val getListOfUnsplashPhotosSortByIdDatabaseUseCase: GetListOfUnsplashPhotosSortByIdDatabaseUseCase,
    private val searchUnsplashPhotoDatabaseUseCase: SearchUnsplashPhotoDatabaseUseCase,
) : ViewModel() {

    private val getUnsplashPhotosDatabaseMutableLiveData: MutableLiveData<Result<List<UnsplashPhotoDetailUi>>> = MutableLiveData(Result.loading())
    val getUnsplashPhotosDatabaseUnsplashPhotoLiveData: LiveData<Result<List<UnsplashPhotoDetailUi>>>
        get() = getUnsplashPhotosDatabaseMutableLiveData

    private val getUnsplashPhotosSortByIdDatabaseMutableLiveData: MutableLiveData<Result<List<UnsplashPhotoDetailUi>>> = MutableLiveData(Result.loading())
    val getUnsplashPhotosSortByIdDatabaseLiveData: LiveData<Result<List<UnsplashPhotoDetailUi>>>
        get() = getUnsplashPhotosSortByIdDatabaseMutableLiveData

    private val searchUnsplashPhotoMutableLiveData: MutableLiveData<List<UnsplashPhotoDetailUi>> = MutableLiveData(listOf())
    val searchUnsplashPhotoLiveData: LiveData<List<UnsplashPhotoDetailUi>>
        get() = searchUnsplashPhotoMutableLiveData

    init {
        getUnsplashPhotosDatabase()
        getUnsplashPhotosSortByIdDatabase()
    }

    private fun getUnsplashPhotosDatabase() {
        getListOfUnsplashPhotosDatabaseUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Result.success(DetailDomainListToDetailUiListMapper.map(it.data))
            }.subscribe {
                getUnsplashPhotosDatabaseMutableLiveData.value = it
            }
    }

    private fun getUnsplashPhotosSortByIdDatabase() {
        getListOfUnsplashPhotosSortByIdDatabaseUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Result.success(DetailDomainListToDetailUiListMapper.map(it.data))
            }.subscribe {
                getUnsplashPhotosSortByIdDatabaseMutableLiveData.value = it
            }
    }

    fun delete(unsplashPhoto: UnsplashPhotoDetailUi) {
        Log.d("PetProject", "fun delete")
        deleteUnsplashPhotoDatabaseUseCase
            .execute(DetailUiToDetailDomainMapper.map(unsplashPhoto))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { // onComplete
                    Log.d("PetProject", "Completed delete")
                },
                { // onError
                        error ->
                    Log.d("PetProject", "Error delete $error")
                }
            )
    }

    fun insert(unsplashPhoto: UnsplashPhotoDetailUi) {
        Log.d("PetProject", "fun insert")
        insertUnsplashPhotoDatabaseUseCase
            .execute(DetailUiToDetailDomainMapper.map(unsplashPhoto))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { // onComplete
                    Log.d("PetProject", "Completed insert")
                },
                { // onError
                        error ->
                    Log.d("PetProject", "Error insert $error")
                }
            )
    }

    fun deleteAll() {
        deleteAllUnsplashPhotoDatabaseUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { // onComplete
                    Log.d("PetProject", "Completed deleteAll")
                },
                { // onError
                        error ->
                    Log.d("PetProject", "Error deleteAll $error")
                }
            )
    }

    fun searchDatabase(searchQuery: String) {
        searchUnsplashPhotoDatabaseUseCase.execute(searchQuery)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Result.success(DetailDomainListToDetailUiListMapper.map(it.data))
            }.subscribe {
                it.data?.let {
                    searchUnsplashPhotoMutableLiveData.value = it
                }
            }
    }
}