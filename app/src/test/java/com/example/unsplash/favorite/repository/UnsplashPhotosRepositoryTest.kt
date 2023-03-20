package com.example.unsplash.favorite.repository

import app.cash.turbine.testIn
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.features.favoriteunsplashphotos.data.repository.UnsplashPhotosRepositoryImpl
import com.example.unsplash.features.favoriteunsplashphotos.domain.UnsplashPhotosRepository
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDBDataSource
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class UnsplashPhotosRepositoryTest {

    private var mockUnsplashPhotoDBDataSource: UnsplashPhotoDBDataSource = mockk()

    private lateinit var repository: UnsplashPhotosRepository

    @Test
    fun positiveRepositoryTest() = runTest {
        repository = UnsplashPhotosRepositoryImpl(mockUnsplashPhotoDBDataSource)

        var photo = UnsplashPhotoDatabase(1, "unsplashPhotoIdTest", "urlsRegularTest", true)
        var flowListOfPhotos: Flow<Result<List<UnsplashPhotoDatabase>>> = flowOf(
            Result.success(listOf(photo)))

        coEvery {
            mockUnsplashPhotoDBDataSource.getAllUnsplashPhotos()
        } returns flowListOfPhotos

        var result: Result<List<UnsplashPhotoDetailDomain>> =
            repository.getAllUnsplashPhotos().testIn(CoroutineScope(Dispatchers.Default)).awaitItem()
        Assert.assertEquals(result.data?.get(0)?.id, photo.id)
        Assert.assertEquals(result.data?.get(0)?.unsplashPhotoId, photo.unsplashPhotoId)
        Assert.assertEquals(result.data?.get(0)?.urlsRegular, photo.urlsRegular)
        Assert.assertEquals(result.data?.get(0)?.isSaved, photo.isSaved)
    }
}