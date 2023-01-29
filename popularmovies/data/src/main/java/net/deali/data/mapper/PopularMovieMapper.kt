package net.deali.data.mapper

import net.deali.data.response.PopularMovieResponse
import net.deali.nativecore.Movie
import net.deali.domain.model.PopularMovieEntity
import net.deali.nativecore.resizeImage

fun PopularMovieResponse.toModel(): PopularMovieEntity {
    return PopularMovieEntity(
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
}