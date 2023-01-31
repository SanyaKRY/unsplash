package com.example.unsplash.features.unsplashphotoanduserdetails.presenter.mapper

import com.example.unsplash.core.mapper.BaseMapper
import com.example.unsplash.features.unsplashphotoanduserdetails.presenter.model.Likes
import com.example.unsplash.features.unsplashphotoanduserdetails.presenter.model.Links
import com.example.unsplash.features.unsplashphotoanduserdetails.presenter.model.UnsplashPhotoAndUserDetailsUi
import com.example.unsplash.features.unsplashphotoanduserdetails.presenter.model.User
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi

object UiToUnsplashPhotoAndUserUiMapper : BaseMapper<UnsplashPhotoUi, UnsplashPhotoAndUserDetailsUi> {
    override fun map(type: UnsplashPhotoUi?): UnsplashPhotoAndUserDetailsUi {
        return UnsplashPhotoAndUserDetailsUi(
            id = type?.id ?: "",
            urlsRegular = type?.urlsRegular ?: "",
            Likes(likes = type?.unsplashPhotoDetailsBundleModel?.likes ?: 0),
            User(
                username = type?.userDetailsBundleModel?.username ?: "",
                Links(html = type?.userDetailsBundleModel?.userLinksDetailsBundleModel?.html ?: "")
            )
        )
    }
}