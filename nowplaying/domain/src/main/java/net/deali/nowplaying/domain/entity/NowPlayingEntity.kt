package net.deali.nowplaying.domain.entity

import net.deali.coredomain.entity.BaseEntity
import net.deali.coredomain.entity.MovieEntity

data class NowPlayingEntity(
    var totalPageCount: Int = 1,
    val movieEntities: List<MovieEntity> = listOf(),
) : BaseEntity()