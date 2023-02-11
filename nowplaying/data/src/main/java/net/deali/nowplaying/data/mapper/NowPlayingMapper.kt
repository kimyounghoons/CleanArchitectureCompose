package net.deali.nowplaying.data.mapper

import net.deali.coredata.mapper.Mapper
import net.deali.coredata.util.resizeImage
import net.deali.coredomain.entity.MovieEntity
import net.deali.nowplaying.data.response.NowPlayingResponse
import net.deali.nowplaying.domain.entity.NowPlayingEntity
import javax.inject.Inject

class NowPlayingMapper @Inject constructor() : Mapper<NowPlayingResponse, NowPlayingEntity> {
    override fun toModel(response: NowPlayingResponse): NowPlayingEntity = response.run {
        NowPlayingEntity(
            totalPageCount = totalPages ?: 1,
            movieEntities = results?.map {
                MovieEntity(
                    title = it.title ?: "",
                    isAdult = it.adult ?: false,
                    imageUrl = it.posterPath?.resizeImage(300) ?: "",
                    backdropUrl = it.backdropPath?.resizeImage(400) ?: "",
                    releaseDate = it.releaseDate ?: "",
                    overview = it.overview ?: ""
                )
            } ?: listOf(),
        )
    }
}