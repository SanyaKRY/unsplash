package com.example.unsplash.features.unsplashphotodetail.data.datasource.database

import android.util.Log
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.dao.UnsplashPhotoDao
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.data.repository.mapper.DomainToDatabaseMapper
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import kotlinx.coroutines.flow.Flow
import com.example.unsplash.core.datatype.Result
import kotlinx.coroutines.flow.flow
import java.lang.Error
import java.lang.Exception
import javax.inject.Inject

class UnsplashPhotoDBDataSource @Inject constructor(private val unsplashPhotoDao: UnsplashPhotoDao) {

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

    fun getAllUnsplashPhotos(): Flow<Result<List<UnsplashPhotoDatabase>>> {
        return flow {
            try {
                unsplashPhotoDao.getAllUnsplashPhotos()
                    .collect { list: List<UnsplashPhotoDatabase> ->
                        emit(Result.success(list))
                    }
            } catch (ex: Exception) {
                Result.error<Error>(Exception(ex))
            }
        }
    }

    fun getAllUnsplashPhotosSortById(): Flow<Result<List<UnsplashPhotoDatabase>>> {
        return flow {
            try {
                unsplashPhotoDao.getAllUnsplashPhotosSortById()
                    .collect { list: List<UnsplashPhotoDatabase> ->
                        if (list.isNotEmpty()) {
                            emit(Result.success(list))
                        } else {
                            emit(Result.error(Exception("empty")))
                        }
                    }
            } catch (ex: Exception) {
                Result.error<Error>(Exception(ex))
            }
        }
    }

    fun searchUnsplashPhotoByQuery(searchQuery: String): Flow<Result<List<UnsplashPhotoDatabase>>> {
        Log.d("PetProjectSearch", "searchUnsplashPhotoByQuery $searchQuery")
        return flow {
            try {
                unsplashPhotoDao.searchByQuery(searchQuery)
                    .collect { list: List<UnsplashPhotoDatabase> ->
                        if (list.isNotEmpty()) {
                            emit(Result.success(list))
                            Log.d("PetProjectSearch", "list.size: ${list.size}")
                        } else {
                            emit(Result.error(Exception("empty")))
                        }
                    }
            } catch (ex: Exception) {
                Result.error<Error>(Exception(ex))
            }
        }
    }
}