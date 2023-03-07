package com.example.unsplash.features.favoriteunsplashphotos.data.repository

import android.util.Log
import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDBDataSource
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.data.repository.mapper.DatabaseListToDetailDomainListMapper
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import kotlinx.coroutines.flow.Flow
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.core.datatype.ResultType
import kotlinx.coroutines.flow.flow

class UnsplashPhotosRepositoryImpl(
    private val unsplashPhotoDBDataSource: UnsplashPhotoDBDataSource
) : UnsplashPhotosRepository {

    override fun getAllUnsplashPhotos(): Flow<Result<List<UnsplashPhotoDetailDomain>>> {
        var flow: Flow<Result<List<UnsplashPhotoDetailDomain>>> = flow {
            unsplashPhotoDBDataSource.getAllUnsplashPhotos().let { flowResult: Flow<Result<List<UnsplashPhotoDatabase>>> ->
                flowResult.collect { result: Result<List<UnsplashPhotoDatabase>> ->
                    if (result.resultType == ResultType.SUCCESS) {
                        var list: List<UnsplashPhotoDetailDomain> = DatabaseListToDetailDomainListMapper.map(result.data)
                        emit(Result.success(list))
                    } else if (result.resultType == ResultType.ERROR) {
                        emit(Result.error(result.error))
                    }
                }
            }
        }
        return flow
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


    override fun getAllUnsplashPhotosSortById(): Flow<Result<List<UnsplashPhotoDetailDomain>>> {
        var flow: Flow<Result<List<UnsplashPhotoDetailDomain>>> = flow {
            unsplashPhotoDBDataSource.getAllUnsplashPhotosSortById().let { flowResult: Flow<Result<List<UnsplashPhotoDatabase>>> ->
                flowResult.collect { result: Result<List<UnsplashPhotoDatabase>> ->
                    if (result.resultType == ResultType.SUCCESS) {
                        var list: List<UnsplashPhotoDetailDomain> = DatabaseListToDetailDomainListMapper.map(result.data)
                        emit(Result.success(list))
                    } else if (result.resultType == ResultType.ERROR) {
                        emit(Result.error(result.error))
                    }
                }
            }
        }
        return flow
    }

    override fun searchUnsplashPhoto(searchQuery: String): Flow<Result<List<UnsplashPhotoDetailDomain>>> {
        Log.d("PetProjectSearch", "searchUnsplashPhoto $searchQuery")
        var flow: Flow<Result<List<UnsplashPhotoDetailDomain>>> = flow {
            unsplashPhotoDBDataSource.searchUnsplashPhotoByQuery(searchQuery).let { flowResult: Flow<Result<List<UnsplashPhotoDatabase>>> ->
                flowResult.collect { result: Result<List<UnsplashPhotoDatabase>> ->
                    if (result.resultType == ResultType.SUCCESS) {
                        Log.d("PetProjectSearch", "result.data!!.size: ${result.data!!.size}")
                        var list: List<UnsplashPhotoDetailDomain> = DatabaseListToDetailDomainListMapper.map(result.data)
                        emit(Result.success(list))
                    } else if (result.resultType == ResultType.ERROR) {
                        emit(Result.error(result.error))
                    }
                }
            }
        }
        return flow
    }
}