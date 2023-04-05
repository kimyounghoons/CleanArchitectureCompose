package net.deali.data.repository

import net.deali.coredata.repository.BaseRepository
import net.deali.data.mapper.toDomainModel
import net.deali.data.service.SearchApiService
import net.deali.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val service: SearchApiService
) : BaseRepository(), SearchRepository {

    override suspend fun getSearchMovies(
        query: String,
        page: Int,
        includeAdult: Boolean?,
        region: String?
    ) = safeResult {
        service.getMovieSearch(
            query = query,
            page = page,
            includeAdult = includeAdult,
            region = region
        ).toDomainModel()
    }

    override suspend fun getMovieSearchKeywords(
        query: String,
        page: Int
    ) = safeResult {
        service.getMovieSearchKeyword(
            query = query,
            page = page
        ).toDomainModel()
    }
}