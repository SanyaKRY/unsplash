package com.example.unsplash.detail.repository

import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.UnsplashPhotoDBDataSource
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.data.repository.UnsplashPhotoDetailRepositoryImpl
import com.example.unsplash.features.unsplashphotodetail.domain.UnsplashPhotoDetailRepository
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class UnsplashPhotoDetailRepositoryTest {

    private var mockUnsplashPhotoDBDataSource: UnsplashPhotoDBDataSource = mockk()

    private lateinit var repository: UnsplashPhotoDetailRepository

    @Test
    fun positiveRepositoryTest() = runTest {
        repository = UnsplashPhotoDetailRepositoryImpl(mockUnsplashPhotoDBDataSource)

        var unsplashPhoto =
            UnsplashPhotoDetailDomain(
                12345, "testUnsplashPhotoIdTest", "testUrlRegularTest", true
            )

        coEvery {
            mockUnsplashPhotoDBDataSource.searchUnsplashPhoto(unsplashPhoto)
        } returns UnsplashPhotoDatabase(12345, "testUnsplashPhotoIdTest", "testUrlRegularTest", true)

        var unsplashPhotoRepository = repository.searchUnsplashPhoto(unsplashPhoto)
        Assert.assertEquals(unsplashPhotoRepository?.id, unsplashPhoto.id)
        Assert.assertEquals(unsplashPhotoRepository?.unsplashPhotoId, unsplashPhoto.unsplashPhotoId)
        Assert.assertEquals(unsplashPhotoRepository?.urlsRegular, unsplashPhoto.urlsRegular)
        Assert.assertEquals(unsplashPhotoRepository?.isSaved, unsplashPhoto.isSaved)
    }
}