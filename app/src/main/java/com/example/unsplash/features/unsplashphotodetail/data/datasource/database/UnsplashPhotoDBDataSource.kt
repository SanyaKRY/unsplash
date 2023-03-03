package com.example.unsplash.features.unsplashphotodetail.data.datasource.database

import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.dao.UnsplashPhotoDao
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.data.repository.mapper.DomainToDatabaseMapper
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe

class UnsplashPhotoDBDataSource(private val unsplashPhotoDao: UnsplashPhotoDao) {

    fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): Completable {
        return unsplashPhotoDao.insert(DomainToDatabaseMapper.map(unsplashPhoto))
    }

    fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): Completable {
        return unsplashPhotoDao.delete(DomainToDatabaseMapper.map(unsplashPhoto))
    }

    fun deleteUnsplashPhotoByUnsplashPhotoId(unsplashPhoto: UnsplashPhotoDetailDomain): Completable {
        return unsplashPhotoDao.deleteByUnsplashPhotoId(DomainToDatabaseMapper.map(unsplashPhoto).unsplashPhotoId)
    }

    fun deleteAllUnsplashPhoto(): Completable {
        return unsplashPhotoDao.deleteAll()
    }

    fun searchUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): Maybe<UnsplashPhotoDatabase> {
        return unsplashPhotoDao.search(DomainToDatabaseMapper.map(unsplashPhoto).unsplashPhotoId)
    }

    fun getAllUnsplashPhotos(): Flowable<List<UnsplashPhotoDatabase>> {
        return unsplashPhotoDao.getAllUnsplashPhotos()
    }

    fun getAllUnsplashPhotosSortById(): Flowable<List<UnsplashPhotoDatabase>> {
        return unsplashPhotoDao.getAllUnsplashPhotosSortById()
    }

    fun searchUnsplashPhotoByQuery(searchQuery: String): Flowable<List<UnsplashPhotoDatabase>> {
        return unsplashPhotoDao.searchByQuery(searchQuery)
    }
}