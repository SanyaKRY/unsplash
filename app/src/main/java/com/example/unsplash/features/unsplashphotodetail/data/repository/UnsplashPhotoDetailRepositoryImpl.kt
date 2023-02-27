package com.example.unsplash.features.unsplashphotodetail.data.repository

import android.util.Log
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDBDataSource
import com.example.unsplash.features.unsplashphotodetail.data.repository.mapper.DatabaseToDetailDomainMapper
import com.example.unsplash.features.unsplashphotodetail.domain.UnsplashPhotoDetailRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.core.datatype.ResultType
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class UnsplashPhotoDetailRepositoryImpl(private val unsplashPhotoDBDataSource: UnsplashPhotoDBDataSource) : UnsplashPhotoDetailRepository {

    override fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): Completable {
        return unsplashPhotoDBDataSource.insertUnsplashPhoto(unsplashPhoto)
    }

    override fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): Completable {
        return unsplashPhotoDBDataSource.deleteUnsplashPhoto(unsplashPhoto)
    }

    override fun deleteUnsplashPhotoByUnsplashPhotoId(unsplashPhoto: UnsplashPhotoDetailDomain): Completable {
        return unsplashPhotoDBDataSource.deleteUnsplashPhotoByUnsplashPhotoId(unsplashPhoto)
    }

    override fun searchUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): Maybe<Result<UnsplashPhotoDetailDomain>> {
        return unsplashPhotoDBDataSource.searchUnsplashPhoto(unsplashPhoto).map {
            if (it.resultType == ResultType.SUCCESS) {
                Result.success(DatabaseToDetailDomainMapper.map(it.data))
            } else {
                Result.error(it.error)
            }
        }
    }
}
