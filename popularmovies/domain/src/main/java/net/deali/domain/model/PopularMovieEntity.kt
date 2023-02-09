package net.deali.domain.model

import net.deali.coredomain.entity.BaseEntity
import net.deali.coredomain.entity.MovieEntity

data class PopularMovieEntity(
    var totalPageCount: Int = 1,
    val movieEntities: List<MovieEntity> = listOf(),
) : BaseEntity()