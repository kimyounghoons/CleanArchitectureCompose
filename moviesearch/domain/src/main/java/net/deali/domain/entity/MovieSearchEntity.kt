package net.deali.domain.entity

import net.deali.nativecore.Movie

data class MovieSearchEntity(
    val movies: List<Movie> = listOf()
)