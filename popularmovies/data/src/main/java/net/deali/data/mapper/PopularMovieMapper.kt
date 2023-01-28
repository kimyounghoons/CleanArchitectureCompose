package net.deali.data.mapper

import net.deali.data.dto.PopularMovieDTO
import net.deali.domain.model.Movie
import net.deali.domain.model.PopularMovies
import net.deali.nativecore.resizeImage

fun PopularMovieDTO.toModel(): PopularMovies {
    return PopularMovies(
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