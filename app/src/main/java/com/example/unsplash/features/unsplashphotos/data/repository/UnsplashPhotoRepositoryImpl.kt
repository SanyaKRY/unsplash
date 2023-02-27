package com.example.unsplash.features.unsplashphotos.data.repository

import android.util.Log
import androidx.paging.*
import androidx.paging.rxjava2.observable
import com.example.unsplash.features.unsplashphotos.data.datasource.api.UnsplashPhotoNetworkDataSource
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.UnsplashPhotoApi
import com.example.unsplash.features.unsplashphotos.data.repository.mapper.PagingApiToPagingDomainMapper
import com.example.unsplash.features.unsplashphotos.domain.UnsplashPhotoRepository
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain
import com.example.unsplash.features.unsplashphotos.data.paging.UncplashPhotoPagingSource
import io.reactivex.Observable

const val MAX_RESULTS_PER_PAGE: Int = 10

class UnsplashPhotoRepositoryImpl(
    private val unsplashPhotoNetworkDataSource: UnsplashPhotoNetworkDataSource
) : UnsplashPhotoRepository {

    override fun getListOfUnsplashPhotos(): Observable<PagingData<UnsplashPhotoDomain>> {

        Log.d("PetProject", "call getListOfUnsplashPhotos(), class UnsplashPhotoRepositoryImpl")

        var pager =  Pager(
            config = PagingConfig(
                pageSize = MAX_RESULTS_PER_PAGE
            ),
            pagingSourceFactory = {
                UncplashPhotoPagingSource(unsplashPhotoNetworkDataSource = unsplashPhotoNetworkDataSource)
            }
        )
        return pager.observable.map { pagingData: PagingData<UnsplashPhotoApi> ->
            pagingData.map { unsplashPhotoApi: UnsplashPhotoApi ->
                PagingApiToPagingDomainMapper.map(unsplashPhotoApi)
            }
        }
    }
}