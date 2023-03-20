package com.example.unsplash.photos.mapper

import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.Links
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.UnsplashPhotoApi
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.Urls
import com.example.unsplash.features.unsplashphotos.data.datasource.api.model.User
import com.example.unsplash.features.unsplashphotos.domain.model.User as UserDomain
import com.example.unsplash.features.unsplashphotos.domain.model.Links as UserLinks
import com.example.unsplash.features.unsplashphotos.data.repository.mapper.PagingApiToPagingDomainMapper
import com.example.unsplash.features.unsplashphotos.domain.model.UnsplashPhotoDomain
import com.example.unsplash.features.unsplashphotos.presentation.mapper.PagingDomainToUiMapper
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi
import org.junit.Assert
import org.junit.Test

class MapperTest {

    @Test
    fun positiveApiToPagingDomainMapperTest() {
        var photo: UnsplashPhotoApi =
            UnsplashPhotoApi("id", Urls("regular"), 5, User("username", Links("html")))

        var unsplashPhotoDomain: UnsplashPhotoDomain = PagingApiToPagingDomainMapper.map(photo)
        Assert.assertEquals(photo.unsplashPhotoId, unsplashPhotoDomain.unsplashPhotoId)
        Assert.assertEquals(photo.urls.regular, unsplashPhotoDomain.urlsRegular)
        Assert.assertEquals(photo.likes, unsplashPhotoDomain.likes)
        Assert.assertEquals(photo.user.username, unsplashPhotoDomain.user.username)
        Assert.assertEquals(photo.user.links.html, unsplashPhotoDomain.user.links.html)
    }

    @Test
    fun positiveDomainToUiMapperMapperTest() {
        var photo: UnsplashPhotoDomain =
            UnsplashPhotoDomain("id", "regular", 5, UserDomain("username", UserLinks("html")))

        var unsplashPhotoUi: UnsplashPhotoUi = PagingDomainToUiMapper.map(photo)
        Assert.assertEquals(photo.unsplashPhotoId, unsplashPhotoUi.unsplashPhotoId)
        Assert.assertEquals(photo.urlsRegular, unsplashPhotoUi.urlsRegular)
        Assert.assertEquals(photo.unsplashPhotoId, unsplashPhotoUi.unsplashPhotoDetailsBundleModel.id)
        Assert.assertEquals(photo.urlsRegular, unsplashPhotoUi.unsplashPhotoDetailsBundleModel.urlsRegular)
        Assert.assertEquals(photo.likes, unsplashPhotoUi.unsplashPhotoDetailsBundleModel.likes)
        Assert.assertEquals(photo.user.username, unsplashPhotoUi.unsplashPhotoDetailsBundleModel.user.username)
    }
}