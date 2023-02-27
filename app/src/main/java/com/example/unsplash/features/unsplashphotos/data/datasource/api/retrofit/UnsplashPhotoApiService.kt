package com.example.unsplash.features.unsplashphotos.data.datasource.api.retrofit

import com.example.unsplash.BuildConfig
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.UnsplashPhotoApi
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashPhotoApiService {

    @Headers("Authorization: Client-ID ${BuildConfig.API_ACCESS_KEY}")
    @GET("/photos")
    fun getListOfUnsplashPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Single<List<UnsplashPhotoApi>?>
}