package com.example.unsplash.features.unsplashphotos.data.paging

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.unsplash.features.unsplashphotos.data.datasource.api.UnsplashPhotoNetworkDataSource
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.UnsplashPhotoApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class UncplashPhotoPagingSource(
    private val unsplashPhotoNetworkDataSource: UnsplashPhotoNetworkDataSource,
) : RxPagingSource<Int, UnsplashPhotoApi>() {

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhotoApi>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun toLoadResult(data: List<UnsplashPhotoApi>, currentPage: Int, pageSize: Int): LoadResult<Int, UnsplashPhotoApi> {
        return LoadResult.Page(
            data = data,
            prevKey = if (currentPage == 1) null else currentPage - 1,
            nextKey = if (data.size < pageSize) null else currentPage + 1
        )
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, UnsplashPhotoApi>> {
        val currentPage = params.key ?: 1
        val pageSize: Int = params.loadSize
        return unsplashPhotoNetworkDataSource.getListOfUnsplashPhotos(currentPage, pageSize)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, currentPage, pageSize) }
            .onErrorReturn { LoadResult.Error(it) }
    }
}