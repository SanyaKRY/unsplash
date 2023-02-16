package com.example.unsplash.features.di

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.DeleteUnsplashPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.InsertUnsplashPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.domain.usecase.IsSavedUnsplashPhotoUseCase
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import com.example.unsplash.features.unsplashphotodetail.presenter.vm.UnsplashPhotoDetailViewModel
import com.example.unsplash.features.unsplashphotos.domain.usecase.GetListOfUnsplashPhotosUseCase
import com.example.unsplash.features.unsplashphotos.presentation.ui.paging.UnsplashPhotoPagingAdapter
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi
import com.example.unsplash.features.unsplashphotos.presentation.ui.recyclerview.UnsplashPhotosAdapter
import com.example.unsplash.features.unsplashphotos.presentation.vm.UnsplashPhotoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    factory { GetListOfUnsplashPhotosUseCase(repository = get()) }
    viewModel {
        UnsplashPhotoViewModel(
            getListOfUnsplashPhotosUseCase = get()
        )
    }
    factory { InsertUnsplashPhotoUseCase(repository = get()) }
    factory { DeleteUnsplashPhotoUseCase(repository = get()) }
    factory { IsSavedUnsplashPhotoUseCase(repository = get()) }
    viewModel {
        (unsplashPhotoDetailUi: UnsplashPhotoDetailUi) ->
        UnsplashPhotoDetailViewModel(
            unsplashPhoto = unsplashPhotoDetailUi,
            insertUnsplashPhotoUseCase = get(),
            deleteUnsplashPhotoUseCase = get(),
            isSavedUnsplashPhotoUseCase = get()
        )
    }
    factory { (unsplashPhotoDetailListener: (unsplashPhotoUi: UnsplashPhotoUi, AppCompatImageView, TextView) -> Unit,
                  unsplashPhotoAndUserDetailsListener: (unsplashPhotoUi: UnsplashPhotoUi) -> Unit) ->
        UnsplashPhotoPagingAdapter(unsplashPhotoDetailListener = unsplashPhotoDetailListener,
            unsplashPhotoAndUserDetailsListener = unsplashPhotoAndUserDetailsListener) }

    // TODO don't use
    factory { (unsplashPhotoDetailListener: (unsplashPhotoUi: UnsplashPhotoUi, AppCompatImageView, TextView) -> Unit,
                  unsplashPhotoAndUserDetailsListener: (unsplashPhotoUi: UnsplashPhotoUi) -> Unit) ->
        UnsplashPhotosAdapter(unsplashPhotoDetailListener = unsplashPhotoDetailListener,
            unsplashPhotoAndUserDetailsListener = unsplashPhotoAndUserDetailsListener) }
}