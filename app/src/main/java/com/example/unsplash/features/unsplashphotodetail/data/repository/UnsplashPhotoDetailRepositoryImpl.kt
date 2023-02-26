package com.example.unsplash.features.unsplashphotodetail.data.repository

import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDBDataSource
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.data.repository.mapper.DatabaseToDetailDomainMapper
import com.example.unsplash.features.unsplashphotodetail.domain.UnsplashPhotoDetailRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain

class UnsplashPhotoDetailRepositoryImpl(private val unsplashPhotoDBDataSource: UnsplashPhotoDBDataSource) : UnsplashPhotoDetailRepository {

    override suspend fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain) {
        return unsplashPhotoDBDataSource.insertUnsplashPhoto(unsplashPhoto)
    }

    override suspend fun deleteUnsplashPhotoByUnsplashPhotoId(unsplashPhoto: UnsplashPhotoDetailDomain) {
        return unsplashPhotoDBDataSource.deleteUnsplashPhotoByUnsplashPhotoId(unsplashPhoto)
    }

    override suspend fun searchUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): UnsplashPhotoDetailDomain? {
        var unsplashPhotoDatabase: UnsplashPhotoDatabase? = unsplashPhotoDBDataSource.searchUnsplashPhoto(unsplashPhoto)
        return if (unsplashPhotoDatabase != null) {
            DatabaseToDetailDomainMapper.map(unsplashPhotoDatabase)
        } else {
            null
        }
    }
}
