package com.example.unsplash.features.unsplashphotodetail.data.repository

import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDBDataSource
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.data.repository.mapper.DatabaseToDetailDomainMapper
import com.example.unsplash.features.unsplashphotodetail.domain.UnsplashPhotoDetailRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashPhotoDetailRepositoryImpl @Inject constructor(
    private val unsplashPhotoDBDataSource: UnsplashPhotoDBDataSource
) : UnsplashPhotoDetailRepository {

    override suspend fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain) {
        return unsplashPhotoDBDataSource.insertUnsplashPhoto(unsplashPhoto)
    }

    override suspend fun deleteUnsplashtByIdPhoto(unsplashPhoto: UnsplashPhotoDetailDomain) {
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
