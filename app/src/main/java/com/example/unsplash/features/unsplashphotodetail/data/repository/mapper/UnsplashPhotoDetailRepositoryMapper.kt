package com.example.unsplash.features.unsplashphotodetail.data.repository.mapper

import com.example.unsplash.core.mapper.BaseMapper
import com.example.unsplash.features.unsplashphotodetail.data.datasource.database.model.UnsplashPhotoDatabase
import com.example.unsplash.features.unsplashphotodetail.domain.model.UnsplashPhotoDetailDomain

object DomainToDatabaseMapper : BaseMapper<UnsplashPhotoDetailDomain, UnsplashPhotoDatabase> {
    override fun map(type: UnsplashPhotoDetailDomain?): UnsplashPhotoDatabase {
        return UnsplashPhotoDatabase(
            id = type?.id ?: 0,
            unsplashPhotoId = type?.unsplashPhotoId ?: "",
            urlsRegular = type?.urlsRegular ?: "",
            isSaved = type?.isSaved
        )
    }
}

object DatabaseToDetailDomainMapper : BaseMapper<UnsplashPhotoDatabase, UnsplashPhotoDetailDomain> {
    override fun map(type: UnsplashPhotoDatabase?): UnsplashPhotoDetailDomain {
        return UnsplashPhotoDetailDomain(
            id = type!!.id,
            unsplashPhotoId = type.unsplashPhotoId,
            urlsRegular = type.urlsRegular,
            isSaved = type.isSaved
        )
    }
}

object DatabaseListToDetailDomainListMapper : BaseMapper<List<UnsplashPhotoDatabase>, List<UnsplashPhotoDetailDomain>> {
    override fun map(type: List<UnsplashPhotoDatabase>?): List<UnsplashPhotoDetailDomain> {
        return type?.map {
            UnsplashPhotoDetailDomain(
                id = it!!.id,
                unsplashPhotoId = it.unsplashPhotoId,
                urlsRegular = it.urlsRegular,
                isSaved = it.isSaved
            )
        } ?: emptyList()
    }
}