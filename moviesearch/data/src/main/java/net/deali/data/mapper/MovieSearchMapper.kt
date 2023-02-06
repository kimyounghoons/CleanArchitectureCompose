package net.deali.data.mapper

import net.deali.coredomain.Movie
import net.deali.data.response.MovieSearchResponse
import net.deali.domain.entity.MovieSearchEntity
import net.deali.nativecore.resizeImage

fun MovieSearchResponse.toModel() = MovieSearchEntity(
    totalPageCount = totalPages ?: 1,
    movies = results?.map {
        Movie(
            title = it.title ?: "",
            isAdult = it.adult ?: false,
            imageUrl = it.posterPath?.resizeImage(500) ?: "",
            releaseDate = it.releaseDate ?: "",
            overview = it.overview ?: ""
        )
    } ?: listOf()
)