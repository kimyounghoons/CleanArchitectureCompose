package net.deali.nowplaying.domain.repository

import net.deali.coredomain.Resource
import net.deali.nowplaying.domain.entity.NowPlayingEntity

interface NowPlayingRepository {
    suspend fun getNowPlayingMovies(
        page: Int,
        region: String?,
    ): Resource<NowPlayingEntity>
}