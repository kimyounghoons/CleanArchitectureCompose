package net.deali.data.mapper

import net.deali.coredata.util.resizeImage
import net.deali.coredomain.entity.MovieEntity
import net.deali.data.response.PopularMovieResponse
import net.deali.domain.model.PopularMovieEntity

fun PopularMovieResponse.toDomainModel() = PopularMovieEntity(
    totalPageCount = totalPages ?: 1,
    movieEntities = results?.map {
        MovieEntity(
            id = it.id ?: 0,
            title = it.title ?: "",
            isAdult = it.adult ?: false,
            imageUrl = it.posterPath?.resizeImage(300) ?: "",
            backdropUrl = it.backdropPath?.resizeImage(400) ?: "",
            releaseDate = it.releaseDate ?: "",
            overview = it.overview ?: ""
        )
    } ?: listOf()
)