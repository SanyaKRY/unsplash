package com.example.unsplash.features.unsplashphotodetail.data.repository

import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDBDataSource
import com.example.unsplash.features.unsplashphotodetail.data.repository.mapper.DatabaseToDetailDomainMapper
import com.example.unsplash.features.unsplashphotodetail.domain.UnsplashPhotoDetailRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
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

    override fun searchUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): Maybe<UnsplashPhotoDetailDomain> {
        return unsplashPhotoDBDataSource.searchUnsplashPhoto(unsplashPhoto).map {
            DatabaseToDetailDomainMapper.map(it)
        }
    }
}
