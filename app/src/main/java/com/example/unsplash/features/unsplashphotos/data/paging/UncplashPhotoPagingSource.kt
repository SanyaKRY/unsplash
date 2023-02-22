package com.example.unsplash.features.unsplashphotos.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.unsplash.features.unsplashphotos.data.datasource.api.UnsplashPhotoNetworkDataSource
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.UnsplashPhotoApi

class UncplashPhotoPagingSource(
    private val unsplashPhotoNetworkDataSource: UnsplashPhotoNetworkDataSource,
) : PagingSource<Int, UnsplashPhotoApi>() {

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhotoApi>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhotoApi> {

        // TODO throw exceptions like LoadResult.Error(e)

        // currentPage, номер страницы запрашиваемой, null  если запрашиваем первую странцу
        val currentPage = params.key ?: 1

        val pageSize: Int = params.loadSize
        // количество зарашиваемых элементов, переопределил, с Pager, где 10 указано, тоесть с перва 30, а затем по 10,10,10,..
        Log.d("PetProject", "currentPage: $currentPage, pageSize: $pageSize")

        return try {
            val response: List<UnsplashPhotoApi>? = unsplashPhotoNetworkDataSource.getListOfUnsplashPhotos(
                currentPage = currentPage,
                perPage = pageSize
            )

            Log.d("PetProject", "response.size: ${response?.size}, pageSize: $pageSize")
            response?.forEach { System.out.print(Log.d("ApiTroubleshooting", "${it.unsplashPhotoId} ")) }

            val prevKey = if (currentPage == 1) null else currentPage - 1
            val nextKey = if (response!!.size < pageSize) null else currentPage + 1

            LoadResult.Page(response, prevKey, nextKey)
        } catch (e: Exception) {
            Log.d("PetProject", "class UncplashPhotoPagingSource $e")
            LoadResult.Error(e)
        }
    }
}