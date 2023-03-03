package com.example.unsplash.features.favoriteunsplashphotos.data.repository

import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDBDataSource
import com.example.unsplash.features.unsplashphotodetail.data.repository.mapper.DatabaseListToDetailDomainListMapper
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class UnsplashPhotosRepositoryImpl(
    private val unsplashPhotoDBDataSource: UnsplashPhotoDBDataSource
) : UnsplashPhotosRepository {

    override fun getAllUnsplashPhotos(): Flowable<List<UnsplashPhotoDetailDomain>> {
        return unsplashPhotoDBDataSource.getAllUnsplashPhotos().map {
            DatabaseListToDetailDomainListMapper.map(it)
        }
    }

    override fun deleteAllUnsplashPhoto(): Completable {
        return unsplashPhotoDBDataSource.deleteAllUnsplashPhoto()
    }

    override fun deleteUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): Completable {
        return unsplashPhotoDBDataSource.deleteUnsplashPhoto(unsplashPhoto)
    }

    override fun deleteUnsplashPhotoByUnsplashPhotoId(unsplashPhoto: UnsplashPhotoDetailDomain): Completable {
        return unsplashPhotoDBDataSource.deleteUnsplashPhotoByUnsplashPhotoId(unsplashPhoto)
    }

    override fun insertUnsplashPhoto(unsplashPhoto: UnsplashPhotoDetailDomain): Completable {
        return unsplashPhotoDBDataSource.insertUnsplashPhoto(unsplashPhoto)
    }

    override fun getAllUnsplashPhotosSortById(): Flowable<List<UnsplashPhotoDetailDomain>> {
        return unsplashPhotoDBDataSource.getAllUnsplashPhotosSortById().map {
            DatabaseListToDetailDomainListMapper.map(it)
        }
    }

    override fun searchUnsplashPhoto(searchQuery: String): Flowable<List<UnsplashPhotoDetailDomain>> {
        return unsplashPhotoDBDataSource.searchUnsplashPhotoByQuery(searchQuery).map {
            DatabaseListToDetailDomainListMapper.map(it)
        }
    }
}