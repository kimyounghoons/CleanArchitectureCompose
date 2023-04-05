package net.deali.nowplaying.data.repository

import net.deali.coredata.repository.BaseRepository
import net.deali.coredomain.Resource
import net.deali.nowplaying.data.mapper.toDomainModel
import net.deali.nowplaying.data.service.NowPlayingApiService
import net.deali.nowplaying.domain.entity.NowPlayingEntity
import net.deali.nowplaying.domain.repository.NowPlayingRepository
import javax.inject.Inject

class NowPlayingRepositoryImpl @Inject constructor(
    private val service: NowPlayingApiService
) : BaseRepository(), NowPlayingRepository {
    override suspend fun getNowPlayingMovies(
        page: Int,
        region: String?
    ): Resource<NowPlayingEntity> = safeResult {
        service.getNowPlayingMovies(page, region).toDomainModel()
    }


}