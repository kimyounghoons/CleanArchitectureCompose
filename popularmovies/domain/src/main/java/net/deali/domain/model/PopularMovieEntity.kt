package net.deali.domain.model

import net.deali.coredomain.BaseEntity
import net.deali.coredomain.Movie

data class PopularMovieEntity(
    val movies: List<Movie> = listOf()
) : BaseEntity()