package net.deali.domain.repository

import kotlinx.coroutines.flow.Flow
import net.deali.domain.entity.MovieSearchEntity
import net.deali.nativecore.Resource

interface SearchRepository {
    fun getSearchMovies(
        query: String,
        page: Int,
        includeAdult: Boolean?,
        region: String?,
    ): Flow<Resource<MovieSearchEntity>>
}