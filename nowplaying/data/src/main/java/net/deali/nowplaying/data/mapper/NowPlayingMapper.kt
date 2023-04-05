package net.deali.nowplaying.data.mapper

import net.deali.coredata.util.resizeImage
import net.deali.coredomain.entity.MovieEntity
import net.deali.nowplaying.data.response.NowPlayingResponse
import net.deali.nowplaying.domain.entity.NowPlayingEntity

fun NowPlayingResponse.toDomainModel() = NowPlayingEntity(
    totalPageCount = totalPages ?: 1,
    movieEntities = results?.map {
        MovieEntity(
            id = it.id ?: 0,
            title = it.title ?: "",
            isAdult = it.adult ?: false,
            imageUrl = it.posterPath?.resizeImage(300) ?: "",
            backdropUrl = it.backdropPath?.resizeImage(400) ?: "",
            releaseDate = it.releaseDate ?: "",
            overview = it.overview ?: ""
        )
    } ?: listOf(),
)