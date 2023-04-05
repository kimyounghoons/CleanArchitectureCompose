package net.deali.detail.data.mapper

import net.deali.coredata.util.resizeImage
import net.deali.detail.data.response.DetailResponse
import net.deali.detail.domain.entity.DetailEntity

fun DetailResponse.toDomainModel() = DetailEntity(
    backdropUrl = backdropPath?.resizeImage(300) ?: "",
    imageUrl = posterPath?.resizeImage(300) ?: "",
    popularity = popularity ?: 0.0,
    productionCompanies = productionCompanies?.map {
        DetailEntity.ProductionCompany(
            id = it.id ?: 0,
            imageUrl = it.logoPath?.resizeImage(300) ?: "",
            name = it.name ?: ""
        )
    } ?: listOf(),
    voteAverage = voteAverage ?: 0.0,
    voteCount = voteCount ?: 0.0f,
    overView = overview ?: "",
    budget = budget ?: 0
)