package net.deali.data.repository

import net.deali.coredata.repository.BaseRepository
import net.deali.data.mapper.toModel
import net.deali.data.response.PopularMovieResponse
import net.deali.data.service.PopularApiService
import net.deali.domain.repository.PopularRepository
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val service: PopularApiService
) : BaseRepository(), PopularRepository {
    override suspend fun getPopularMoives(page: Int) = safeResult {
        service.getPopularMoives(page)
    }.toModel()
}