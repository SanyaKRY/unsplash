package com.example.unsplash.features.somefeature.data.repository

import com.example.unsplash.features.somefeature.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDBDataSource
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.data.repository.mapper.DatabaseListToDetailDomainListMapper
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UnsplashPhotosRepositoryImpl(
    private val unsplashPhotoDBDataSource: UnsplashPhotoDBDataSource
) : UnsplashPhotosRepository {

    override fun getAllUnsplashPhotos(): Flow<List<UnsplashPhotoDetailDomain>> {
        return unsplashPhotoDBDataSource.getAllUnsplashPhotos().map { list: List<UnsplashPhotoDatabase> ->
            DatabaseListToDetailDomainListMapper.map(list)
        }
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

    override fun getAllUnsplashPhotosSortById(): Flow<List<UnsplashPhotoDetailDomain>> {
        return unsplashPhotoDBDataSource.getAllUnsplashPhotosSortById().map { list: List<UnsplashPhotoDatabase> ->
            DatabaseListToDetailDomainListMapper.map(list)
        }
    }

    override fun searchUnsplashPhoto(searchQuery: String): Flow<List<UnsplashPhotoDetailDomain>> {
        return unsplashPhotoDBDataSource.searchUnsplashPhotoByQuery(searchQuery).map { list: List<UnsplashPhotoDatabase> ->
            DatabaseListToDetailDomainListMapper.map(list)
        }
    }
}