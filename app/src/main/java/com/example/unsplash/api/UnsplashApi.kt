package com.example.unsplash.api

import com.example.unsplash.BuildConfig
import com.example.unsplash.model.UnsplashPhoto
import com.example.unsplash.util.Constants.SEVEN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Authorization: Client-ID ${BuildConfig.API_ACCESS_KEY}")
    @GET("/photos")
    suspend fun  getListOfPhotos(
        @Query("per_page") perPage: Int
    ): List<UnsplashPhoto>
}

object UnsplashApiImpl {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.unsplash.com")
        .build()

    private val unsplashApiService = retrofit.create(UnsplashApi::class.java)

    suspend fun getListOfPhotos(): List<UnsplashPhoto> {
        return withContext(Dispatchers.IO) {
            unsplashApiService.getListOfPhotos(SEVEN)
        }
    }
}