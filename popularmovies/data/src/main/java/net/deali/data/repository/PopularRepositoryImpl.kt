package net.deali.data.repository

import net.deali.coredata.repository.BaseRepository
import net.deali.data.mapper.PopularMovieMapper
import net.deali.data.service.PopularApiService
import net.deali.domain.repository.PopularRepository
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val service: PopularApiService,
    private val popularMovieMapper: PopularMovieMapper
) : BaseRepository(), PopularRepository {
    override suspend fun getPopularMoives(page: Int) = safeResult(
        mapper = popularMovieMapper
    ) {
        service.getPopularMoives(page)
    }
}
