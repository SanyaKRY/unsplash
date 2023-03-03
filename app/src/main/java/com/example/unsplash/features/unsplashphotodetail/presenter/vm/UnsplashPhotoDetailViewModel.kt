package com.example.unsplash.features.unsplashphotodetail.presenter.vm

import android.util.Log
import androidx.lifecycle.*
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.DeleteUnsplashPhotoByIdPhoyoUseCase
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.InsertUnsplashPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.IsSavedUnsplashPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailUiToDetailDomainMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UnsplashPhotoDetailViewModel(
    private val unsplashPhoto: UnsplashPhotoDetailUi,
    private val insertUnsplashPhotoUseCase: InsertUnsplashPhotoUseCase,
    private val deleteUnsplashPhotoByidPhotoUseCase: DeleteUnsplashPhotoByIdPhoyoUseCase,
    private val isSavedUnsplashPhotoUseCase: IsSavedUnsplashPhotoUseCase
) : ViewModel() {

    private val isSavedUnsplashPhotoMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isSavedUnsplashPhotoLiveData: LiveData<Boolean>
        get() = isSavedUnsplashPhotoMutableLiveData

    init {
        Log.d("UnsplashPhotoLog", "init UnsplashPhotoDetailViewModel ${this.toString()}")
        Log.d(
            "UnsplashPhotoLog",
            "init UnsplashPhotoDetailViewModel unsplashPhoto ${unsplashPhoto.unsplashPhotoId}"
        )
        isSavedUnsplashPhoto(unsplashPhoto)
    }

    fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailUi) {
        Log.d("PetProject", "fun insertUnsplashPhoto")
        insertUnsplashPhotoUseCase
            .execute(DetailUiToDetailDomainMapper.map(unsplashPhoto))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { // onComplete
                    Log.d("PetProject", "Completed insertUnsplashPhoto")
                },
                { // onError
                        error ->
                    Log.d("PetProject", "Error insertUnsplashPhoto $error")
                }
            )
    }

    fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailUi) {
        Log.d("PetProject", "fun delete")
        deleteUnsplashPhotoByidPhotoUseCase
            .execute(DetailUiToDetailDomainMapper.map(unsplashPhoto))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { // onComplete
                    Log.d("PetProject", "Completed deleteUnsplashPhoto")
                },
                { // onError
                        error ->
                    Log.d("PetProject", "Error deleteUnsplashPhoto $error")
                }
            )
    }

    private fun isSavedUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailUi) {
        isSavedUnsplashPhotoUseCase.execute(DetailUiToDetailDomainMapper.map(unsplashPhoto))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { // onSuccess
                        value ->
                    Log.d("PetProject", "isSavedUnsplashPhoto Success $value")
                    isSavedUnsplashPhotoMutableLiveData.value = value
                },
                { // onError
                        error ->
                    Log.d("PetProject", "isSavedUnsplashPhoto Error $error")
                },
                { // onComplete
                    Log.d("PetProject", "isSavedUnsplashPhoto Completed")
                    isSavedUnsplashPhotoMutableLiveData.value = false
                }
            )
    }
}