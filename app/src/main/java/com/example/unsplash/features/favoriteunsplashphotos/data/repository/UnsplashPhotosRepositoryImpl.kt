package com.example.unsplash.features.favoriteunsplashphotos.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDBDataSource
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.data.repository.mapper.DatabaseListToDetailDomainListMapper
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.core.datatype.ResultType

class UnsplashPhotosRepositoryImpl(
    private val unsplashPhotoDBDataSource: UnsplashPhotoDBDataSource
) : UnsplashPhotosRepository {

    override fun getAllUnsplashPhotos(): LiveData<Result<List<UnsplashPhotoDetailDomain>>> {
        var result = MediatorLiveData<Result<List<UnsplashPhotoDetailDomain>>>()
        result.value = Result.loading()
        var source: LiveData<Result<List<UnsplashPhotoDatabase>>> = unsplashPhotoDBDataSource.getAllUnsplashPhotos()
        result.addSource(source) { resultList: Result<List<UnsplashPhotoDatabase>> ->
            if (resultList.resultType == ResultType.SUCCESS) {
                var list: List<UnsplashPhotoDetailDomain> = DatabaseListToDetailDomainListMapper.map(resultList.data)
                result.value = Result.success(list)
            } else if (resultList.resultType == ResultType.ERROR) {
                result.value = Result.error(resultList.error)
            }
        }
        return result
    }

    override suspend fun deleteAllUnsplashPhoto() {
        return unsplashPhotoDBDataSource.deleteAllUnsplashPhoto()
    }

    override suspend fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain) {
        return unsplashPhotoDBDataSource.deleteUnsplashPhoto(unsplashPhoto)
    }

    override suspend fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain) {
        return unsplashPhotoDBDataSource.insertUnsplashPhoto(unsplashPhoto)
    }

    override fun getAllUnsplashPhotosSortById(): LiveData<Result<List<UnsplashPhotoDetailDomain>>> {
        var result = MediatorLiveData<Result<List<UnsplashPhotoDetailDomain>>>()
        result.value = Result.loading()
        var source: LiveData<Result<List<UnsplashPhotoDatabase>>> = unsplashPhotoDBDataSource.getAllUnsplashPhotosSortById()
        result.addSource(source) { resultList: Result<List<UnsplashPhotoDatabase>> ->
            if (resultList.resultType == ResultType.SUCCESS) {
                var list: List<UnsplashPhotoDetailDomain> = DatabaseListToDetailDomainListMapper.map(resultList.data)
                result.value = Result.success(list)
            } else if (resultList.resultType == ResultType.ERROR) {
                result.value = Result.error(resultList.error)
            }
        }
        return result
    }

    override fun searchUnsplashPhoto(searchQuery: String): LiveData<Result<List<UnsplashPhotoDetailDomain>>> {
        var result = MediatorLiveData<Result<List<UnsplashPhotoDetailDomain>>>()
        result.value = Result.loading()
        var source: LiveData<Result<List<UnsplashPhotoDatabase>>> = unsplashPhotoDBDataSource.searchUnsplashPhotoByQuery(searchQuery)
        result.addSource(source) { resultList: Result<List<UnsplashPhotoDatabase>> ->
            if (resultList.resultType == ResultType.SUCCESS) {
                var list: List<UnsplashPhotoDetailDomain> = DatabaseListToDetailDomainListMapper.map(resultList.data)
                result.value = Result.success(list)
            } else if (resultList.resultType == ResultType.ERROR) {
                result.value = Result.error(resultList.error)
            }
        }
        return result
    }
}