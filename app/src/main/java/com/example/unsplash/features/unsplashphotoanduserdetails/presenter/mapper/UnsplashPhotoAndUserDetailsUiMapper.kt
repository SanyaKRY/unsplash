package com.example.unsplash.features.unsplashphotoanduserdetails.presenter.mapper

import com.example.unsplash.core.mapper.BaseMapper
import com.example.unsplash.features.unsplashphotoanduserdetails.presenter.model.Likes
import com.example.unsplash.features.unsplashphotoanduserdetails.presenter.model.Links
import com.example.unsplash.features.unsplashphotoanduserdetails.presenter.model.UnsplashPhotoAndUserDetailsUi
import com.example.unsplash.features.unsplashphotoanduserdetails.presenter.model.User
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoDetailsBundleModel
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi

object UiToUnsplashPhotoAndUserUiMapper : BaseMapper<UnsplashPhotoDetailsBundleModel, UnsplashPhotoAndUserDetailsUi> {
    override fun map(type: UnsplashPhotoDetailsBundleModel?): UnsplashPhotoAndUserDetailsUi {
        return UnsplashPhotoAndUserDetailsUi(
            id = type?.id ?: "",
            urlsRegular = type?.urlsRegular ?: "",
            Likes( likes = type?.likes ?: 0),
            User(
                username = type?.user?.username ?: "",
                Links(html = type?.user?.userLinksDetailsBundleModel?.html ?: "")
            )
        )
    }
}