package com.example.unsplash.features.unsplashphotodetail.data.datasource.database

import androidx.lifecycle.LiveData
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.dao.UnsplashPhotoDao
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.data.repository.mapper.DomainToDatabaseMapper
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain

class UnsplashPhotoDBDataSource(private val unsplashPhotoDao: UnsplashPhotoDao) {

    suspend fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain) {
        return unsplashPhotoDao.insert(DomainToDatabaseMapper.map(unsplashPhoto))
    }

    suspend fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain) {
        return unsplashPhotoDao.delete(DomainToDatabaseMapper.map(unsplashPhoto))
    }

    suspend fun deleteAllUnsplashPhoto() {
        return unsplashPhotoDao.deleteAll()
    }

    suspend fun searchUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): UnsplashPhotoDatabase {
        return unsplashPhotoDao.search(DomainToDatabaseMapper.map(unsplashPhoto).unsplashPhotoId)
    }

    fun getAllUnsplashPhotos(): LiveData<List<UnsplashPhotoDatabase>> {
        return unsplashPhotoDao.getAllUnsplashPhotos()
    }

    fun getAllUnsplashPhotosSortById(): LiveData<List<UnsplashPhotoDatabase>> {
        return unsplashPhotoDao.getAllUnsplashPhotosSortById()
    }

    fun searchUnsplashPhotoByQuery(searchQuery: String): LiveData<List<UnsplashPhotoDatabase>> {
        return unsplashPhotoDao.searchByQuery(searchQuery)
    }
}