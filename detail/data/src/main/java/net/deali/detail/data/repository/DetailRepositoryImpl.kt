package net.deali.detail.data.repository

import net.deali.coredata.repository.BaseRepository
import net.deali.coredomain.Resource
import net.deali.detail.data.mapper.toDomainModel
import net.deali.detail.data.service.DetailApiService
import net.deali.detail.domain.entity.DetailEntity
import net.deali.detail.domain.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val service: DetailApiService
) : BaseRepository(), DetailRepository {
    override suspend fun getDetail(movieId: Int): Resource<DetailEntity> =
        safeResult {
            service.getNowPlayingMovies(movieId).toDomainModel()
        }
}