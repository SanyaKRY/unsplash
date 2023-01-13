package com.example.unsplash.features.di

import com.example.unsplash.features.unsplashphotos.domain.usecase.GetListOfUnsplashPhotosUseCase
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
    factory { UnsplashPhotosAdapter() }
}