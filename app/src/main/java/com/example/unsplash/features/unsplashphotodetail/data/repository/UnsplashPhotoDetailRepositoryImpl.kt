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

    override suspend fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain) {
        return unsplashPhotoDBDataSource.deleteUnsplashPhoto(unsplashPhoto)
    }

    override suspend fun searchUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): UnsplashPhotoDetailDomain? {
        var unsplashPhoto: UnsplashPhotoDatabase? = unsplashPhotoDBDataSource.searchUnsplashPhoto(unsplashPhoto)
        return if (unsplashPhoto != null) {
            DatabaseToDetailDomainMapper.map(unsplashPhoto)
        } else {
            null
        }
    }
}
