package com.example.unsplash.favorite.vm

import app.cash.turbine.test
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.features.favoriteunsplashphotos.domain.usecase.*
import com.example.unsplash.features.favoriteunsplashphotos.presenter.vm.UnsplashPhotoDatabaseViewModel
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
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

class UnsplashPhotoFavoriteViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("thread")

    private var mockGetListOfUnsplashPhotosDatabaseUseCase: GetListOfUnsplashPhotosDatabaseUseCase = mockk()
    private var mockDeleteAllUnsplashPhotoDatabaseUseCase: DeleteAllUnsplashPhotoDatabaseUseCase = mockk()
    private var mockDeleteUnsplashPhotoDatabaseUseCase: DeleteUnsplashPhotoDatabaseUseCase = mockk()
    private var mockInsertUnsplashPhotoDatabaseUseCase: InsertUnsplashPhotoDatabaseUseCase = mockk()
    private var mockGetListOfUnsplashPhotosSortByIdDatabaseUseCase: GetListOfUnsplashPhotosSortByIdDatabaseUseCase = mockk()
    private var mockSearchUnsplashPhotoDatabaseUseCase: SearchUnsplashPhotoDatabaseUseCase = mockk()

    private lateinit var viewModel: UnsplashPhotoDatabaseViewModel

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

        var flowListOfPhotos: Flow<Result<List<UnsplashPhotoDetailDomain>>> = flowOf(Result.success(listOf(
            UnsplashPhotoDetailDomain(1, "unsplashPhotoIdTest", "urlsRegularTest", true)
        )))
        coEvery {
            mockGetListOfUnsplashPhotosDatabaseUseCase.execute()
        } returns flowListOfPhotos

        var flowListOfSortedPhotos: Flow<Result<List<UnsplashPhotoDetailDomain>>> = flowOf(Result.success(listOf(
            UnsplashPhotoDetailDomain(1, "unsplashPhotoIdSorted", "urlsRegularSorted", true)
        )))
        coEvery {
            mockGetListOfUnsplashPhotosSortByIdDatabaseUseCase.execute()
        } returns flowListOfSortedPhotos

        viewModel = UnsplashPhotoDatabaseViewModel(
            getListOfUnsplashPhotosDatabaseUseCase = mockGetListOfUnsplashPhotosDatabaseUseCase,
            deleteAllUnsplashPhotoDatabaseUseCase = mockDeleteAllUnsplashPhotoDatabaseUseCase,
            deleteUnsplashPhotoDatabaseUseCase = mockDeleteUnsplashPhotoDatabaseUseCase,
            insertUnsplashPhotoDatabaseUseCase = mockInsertUnsplashPhotoDatabaseUseCase,
            getListOfUnsplashPhotosSortByIdDatabaseUseCase = mockGetListOfUnsplashPhotosSortByIdDatabaseUseCase,
            searchUnsplashPhotoDatabaseUseCase = mockSearchUnsplashPhotoDatabaseUseCase
        )

        launch(Dispatchers.Main) {
            viewModel.getUnsplashPhotosDatabaseUnsplashPhotoFlow.test {
                Truth.assertThat(awaitItem()).isEqualTo(Result.success(listOf(
                    UnsplashPhotoDetailUi(1, "unsplashPhotoIdTest", "urlsRegularTest", true)
                )))
            }

            viewModel.getUnsplashPhotosSortByIdDatabaseFlow.test {
                Truth.assertThat(awaitItem()).isEqualTo(Result.success(listOf(
                    UnsplashPhotoDetailUi(1, "unsplashPhotoIdSorted", "urlsRegularSorted", true)
                )))
            }
        }
    }
}