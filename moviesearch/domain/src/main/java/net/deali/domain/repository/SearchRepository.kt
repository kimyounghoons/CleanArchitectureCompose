package net.deali.domain.repository

import net.deali.domain.entity.MovieSearchEntity
import net.deali.nativecore.Resource

interface SearchRepository {
    suspend fun getSearchMovies(
        query: String,
        page: Int,
        includeAdult: Boolean?,
        region: String?,
    ): Resource<MovieSearchEntity>
}