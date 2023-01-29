package net.deali.domain.model

import net.deali.nativecore.Movie

data class PopularMovieEntity(
    val movies: List<Movie> = listOf()
)