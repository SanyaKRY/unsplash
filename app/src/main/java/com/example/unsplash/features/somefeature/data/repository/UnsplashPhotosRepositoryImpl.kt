package com.example.unsplash.features.somefeature.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.unsplash.features.somefeature.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDBDataSource
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.data.repository.mapper.DatabaseListToDetailDomainListMapper
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain

class UnsplashPhotosRepositoryImpl(
    private val unsplashPhotoDBDataSource: UnsplashPhotoDBDataSource
) : UnsplashPhotosRepository {

    override fun getAllUnsplashPhotos(): LiveData<List<UnsplashPhotoDetailDomain>> {
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

    override fun getAllUnsplashPhotosSortById(): LiveData<List<UnsplashPhotoDetailDomain>> {
        return unsplashPhotoDBDataSource.getAllUnsplashPhotosSortById().map { list: List<UnsplashPhotoDatabase> ->
            DatabaseListToDetailDomainListMapper.map(list)
        }
    }

    override fun searchUnsplashPhoto(searchQuery: String): LiveData<List<UnsplashPhotoDetailDomain>> {
        return unsplashPhotoDBDataSource.searchUnsplashPhotoByQuery(searchQuery).map { list: List<UnsplashPhotoDatabase> ->
            DatabaseListToDetailDomainListMapper.map(list)
        }
    }
}