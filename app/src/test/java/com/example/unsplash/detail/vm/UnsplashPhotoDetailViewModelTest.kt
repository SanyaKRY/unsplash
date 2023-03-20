package com.example.unsplash.detail.vm

import app.cash.turbine.test
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.DeleteUnsplashPhotoByIdPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.InsertUnsplashPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.IsSavedUnsplashPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import com.example.unsplash.features.unsplashphotodetail.presenter.vm.UnsplashPhotoDetailViewModel
import io.mockk.mockk
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailUiToDetailDomainMapper
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class UnsplashPhotoDetailViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("thread")
    private lateinit var unsplashPhotoDetailUi: UnsplashPhotoDetailUi
    private var mockInsertUnsplashPhotoUseCase: InsertUnsplashPhotoUseCase = mockk()
    private var mockDeleteUnsplashPhotoByIdPhotoUseCase: DeleteUnsplashPhotoByIdPhotoUseCase = mockk()
    private var mockIsSavedUnsplashPhotoUseCase: IsSavedUnsplashPhotoUseCase = mockk()
    private lateinit var viewModel: UnsplashPhotoDetailViewModel

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
        unsplashPhotoDetailUi =
            UnsplashPhotoDetailUi(
                12345, "testUnsplashPhotoIdTest", "testUrlRegularTest", true
            )
        coEvery {
            mockIsSavedUnsplashPhotoUseCase.execute(
                DetailUiToDetailDomainMapper.map(
                    unsplashPhotoDetailUi
                )
            )
        } returns Result.success(true)
        viewModel = UnsplashPhotoDetailViewModel(
            unsplashPhoto = unsplashPhotoDetailUi,
            insertUnsplashPhotoUseCase = mockInsertUnsplashPhotoUseCase,
            deleteUnsplashPhotoByIdPhotoUseCase = mockDeleteUnsplashPhotoByIdPhotoUseCase,
            isSavedUnsplashPhotoUseCase = mockIsSavedUnsplashPhotoUseCase
        )
        launch(Dispatchers.Main) {
            viewModel.isSavedUnsplashPhotoFlow.test {
                assertThat(awaitItem()).isEqualTo(Result.success(true))
            }
        }
    }
}