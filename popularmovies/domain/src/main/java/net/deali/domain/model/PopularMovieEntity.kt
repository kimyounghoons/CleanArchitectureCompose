package net.deali.domain.model

import net.deali.coredomain.BaseEntity
import net.deali.coredomain.Movie

data class PopularMovieEntity(
    var totalPageCount: Int = 1,
    val movies: List<Movie> = listOf()
) : BaseEntity()