package net.deali.domain.entity

import net.deali.coredomain.BaseEntity
import net.deali.coredomain.Movie

data class MovieSearchEntity(
    val movies: List<Movie> = listOf()
): BaseEntity()