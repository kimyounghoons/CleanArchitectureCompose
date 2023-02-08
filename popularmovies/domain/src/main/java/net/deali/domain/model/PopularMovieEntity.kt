package net.deali.domain.model

import net.deali.coredomain.BaseEntity
import net.deali.coredomain.Movie
import net.deali.nativecore.exception.ApiException

data class PopularMovieEntity(
    var totalPageCount: Int = 1,
    val movies: List<Movie> = listOf(),
    override val apiException: ApiException?
) : BaseEntity(apiException)