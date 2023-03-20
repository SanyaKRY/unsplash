package com.example.unsplash.detail.mapper

import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailDomainListToDetailUiListMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.DetailUiToDetailDomainMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.UiToDetailUiMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import com.example.unsplash.features.unsplashphotos.presentation.model.Links
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoDetailsBundleModel
import com.example.unsplash.features.unsplashphotos.presentation.model.UnsplashPhotoUi
import com.example.unsplash.features.unsplashphotos.presentation.model.User
import org.junit.Assert
import org.junit.Test

class MapperTest {

    @Test
    fun positiveUiToDetailUiMapperTest() {
        var unsplashPhotoUi =
            UnsplashPhotoUi(
                "unsplashPhotoId",
                "urlsRegular",
                UnsplashPhotoDetailsBundleModel(
                    "id",
                    "urlsRegular",
                    0,
                    User(
                        "username",
                        Links(
                            "html"
                        )
                    )
                )
            )
        var unsplashPhotoDetailUi: UnsplashPhotoDetailUi = UiToDetailUiMapper.map(unsplashPhotoUi)
        Assert.assertEquals(0, unsplashPhotoDetailUi.id)
        Assert.assertEquals("unsplashPhotoId", unsplashPhotoDetailUi.unsplashPhotoId)
        Assert.assertEquals("urlsRegular", unsplashPhotoDetailUi.urlsRegular)
        Assert.assertEquals(null, unsplashPhotoDetailUi.isSaved)
    }

    @Test
    fun positiveDetailUiToDetailDomainMapperTest() {
        var unsplashPhotoDetailUi =
            UnsplashPhotoDetailUi(
                0,
                "unsplashPhotoId",
                "urlsRegular",
                 false
            )
        var unsplashPhotoDetailDomain: UnsplashPhotoDetailDomain = DetailUiToDetailDomainMapper.map(unsplashPhotoDetailUi)
        Assert.assertEquals(0, unsplashPhotoDetailDomain.id)
        Assert.assertEquals("unsplashPhotoId", unsplashPhotoDetailDomain.unsplashPhotoId)
        Assert.assertEquals("urlsRegular", unsplashPhotoDetailDomain.urlsRegular)
        Assert.assertEquals(false, unsplashPhotoDetailDomain.isSaved)
    }

    @Test
    fun positiveDetailDomainListToDetailUiListMapperTest() {
        var listOfUnsplashPhotoDetailDomain = listOf<UnsplashPhotoDetailDomain>(
            UnsplashPhotoDetailDomain(1, "unsplashPhotoId", "urlsRegular", true)
        )

        var listOfUnsplashPhotoDetailUi = DetailDomainListToDetailUiListMapper.map(listOfUnsplashPhotoDetailDomain)
        Assert.assertEquals(1, listOfUnsplashPhotoDetailUi.get(0).id)
        Assert.assertEquals("unsplashPhotoId", listOfUnsplashPhotoDetailUi.get(0).unsplashPhotoId)
        Assert.assertEquals("urlsRegular", listOfUnsplashPhotoDetailUi.get(0).urlsRegular)
        Assert.assertEquals(false, listOfUnsplashPhotoDetailUi.get(0).isSaved)
    }
}