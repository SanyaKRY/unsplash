package com.example.unsplash.features.unsplashphotodetail.data.datasource.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.dao.UnsplashPhotoDao
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.data.repository.mapper.DomainToDatabaseMapper
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import com.example.unsplash.core.datatype.Result

class UnsplashPhotoDBDataSource(private val unsplashPhotoDao: UnsplashPhotoDao) {

    suspend fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain) {
        return unsplashPhotoDao.insert(DomainToDatabaseMapper.map(unsplashPhoto))
    }

    suspend fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain) {
        return unsplashPhotoDao.delete(DomainToDatabaseMapper.map(unsplashPhoto))
    }

    suspend fun deleteUnsplashPhotoByUnsplashPhotoId(unsplashPhoto: UnsplashPhotoDetailDomain) {
        return unsplashPhotoDao.deleteByUnsplashPhotoId(DomainToDatabaseMapper.map(unsplashPhoto).unsplashPhotoId)
    }

    suspend fun deleteAllUnsplashPhoto() {
        return unsplashPhotoDao.deleteAll()
    }

    suspend fun searchUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): UnsplashPhotoDatabase {
        return unsplashPhotoDao.search(DomainToDatabaseMapper.map(unsplashPhoto).unsplashPhotoId)
    }

    fun getAllUnsplashPhotos(): LiveData<Result<List<UnsplashPhotoDatabase>>> {
        var result = MediatorLiveData<Result<List<UnsplashPhotoDatabase>>>()
        result.value = Result.loading()
        try {
            var source: LiveData<List<UnsplashPhotoDatabase>> = unsplashPhotoDao.getAllUnsplashPhotos()
            result.addSource(source) { list: List<UnsplashPhotoDatabase> ->
                result.value = Result.success(list)
            }
        } catch (ex: Exception) {
            result.value = Result.error(Exception(ex))
        }
        return result
    }

    fun getAllUnsplashPhotosSortById(): LiveData<Result<List<UnsplashPhotoDatabase>>> {
        var result = MediatorLiveData<Result<List<UnsplashPhotoDatabase>>>()
        result.value = Result.loading()
        try {
            var source: LiveData<List<UnsplashPhotoDatabase>> = unsplashPhotoDao.getAllUnsplashPhotosSortById()
            result.addSource(source) { list: List<UnsplashPhotoDatabase> ->
                if (list.isNotEmpty()) {
                    result.value = Result.success(list)
                } else {
                    result.value = Result.error(Exception())
                }
            }
        } catch (ex: Exception) {
            result.value = Result.error(Exception(ex))
        }
        return result
    }

    fun searchUnsplashPhotoByQuery(searchQuery: String): LiveData<Result<List<UnsplashPhotoDatabase>>> {
        var result = MediatorLiveData<Result<List<UnsplashPhotoDatabase>>>()
        result.value = Result.loading()
        try {
            var source: LiveData<List<UnsplashPhotoDatabase>> = unsplashPhotoDao.searchByQuery(searchQuery)
            result.addSource(source) { list: List<UnsplashPhotoDatabase> ->
                if (list.isNotEmpty()) {
                    result.value = Result.success(list)
                } else {
                    result.value = Result.error(Exception())
                }
            }
        } catch (ex: Exception) {
            result.value = Result.error(Exception(ex))
        }
        return result
    }
}