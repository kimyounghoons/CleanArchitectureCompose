package net.deali.domain.entity

import net.deali.coredomain.entity.BaseEntity
import net.deali.coredomain.entity.MovieEntity

data class MovieSearchEntity(
    var totalPageCount: Int = 1,
    val movieEntities: List<MovieEntity> = listOf(),
) : BaseEntity()