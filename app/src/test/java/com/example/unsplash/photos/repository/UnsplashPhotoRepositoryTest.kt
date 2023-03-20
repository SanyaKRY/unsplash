package com.example.unsplash.photos.repository

import com.example.unsplash.features.unsplashphotos.data.datasource.api.UnsplashPhotoNetworkDataSource
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.Links
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.UnsplashPhotoApi
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.Urls
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.User
import com.example.unsplash.features.unsplashphotos.data.repository.UnsplashPhotoRepositoryImpl
import com.example.unsplash.features.unsplashphotos.domain.UnsplashPhotoRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class UnsplashPhotoRepositoryTest {

    private var mockUnsplashPhotoNetworkDataSource: UnsplashPhotoNetworkDataSource = mockk()

    private lateinit var repository: UnsplashPhotoRepository

    @Test
    fun positiveRepositoryTest() = runTest {
        repository = UnsplashPhotoRepositoryImpl(mockUnsplashPhotoNetworkDataSource)

        var listOfUnsplashPhoto = listOf<UnsplashPhotoApi>(
            UnsplashPhotoApi("idTest", Urls("regularTest"), 1, User("usernameTest", Links("htmlTest"))),
            UnsplashPhotoApi("idTest", Urls("regularTest"), 1, User("usernameTest", Links("htmlTest")))
        )

        coEvery {
            mockUnsplashPhotoNetworkDataSource.getListOfUnsplashPhotos(1, 1)
        } returns listOfUnsplashPhoto

        Assert.assertNotNull(repository.getListOfUnsplashPhotos())
    }
}