package com.example.unsplash.photos.vm

import androidx.paging.PagingData
import app.cash.turbine.test
import com.example.unsplash.features.unsplashphotos.domain.model.Links
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain
import com.example.unsplash.features.unsplashphotos.domain.model.User
import com.example.unsplash.features.unsplashphotos.domain.usecase.GetListOfUnsplashPhotosUseCase
import com.example.unsplash.features.unsplashphotos.presentation.vm.UnsplashPhotoViewModel
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class UnsplashPhotoViewModelTest {

    private lateinit var viewModel: UnsplashPhotoViewModel
    private var mockGetListOfUnsplashPhotosUseCase: GetListOfUnsplashPhotosUseCase = mockk()
    private val mainThreadSurrogate = newSingleThreadContext("thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun positiveFlowTest() = runTest {
        var flowPgingData: Flow<PagingData<UnsplashPhotoDomain>> = flowOf(PagingData.from(listOf(
            UnsplashPhotoDomain("unsplashPhotoIdTest", "urlsRegularTest", 55,
                User("usernameTest", Links("htmlTest"))
            ),
            UnsplashPhotoDomain("unsplashPhotoIdTest", "urlsRegularTest", 55,
                User("usernameTest", Links("htmlTest"))
            ),
            UnsplashPhotoDomain("unsplashPhotoIdTest", "urlsRegularTest", 55,
                User("usernameTest", Links("htmlTest"))
            )
        )))
        coEvery {
            mockGetListOfUnsplashPhotosUseCase.execute()
        } returns flowPgingData

        viewModel = UnsplashPhotoViewModel(mockGetListOfUnsplashPhotosUseCase)

        launch(Dispatchers.IO) {
            viewModel.unsplashPhotos.test {
                Truth.assertThat(awaitItem()).isNotNull()
            }
        }
    }
}