package net.deali.domain.repository

import net.deali.domain.entity.MovieSearchEntity

interface SearchRepository {
    suspend fun getSearchMovies(
        query: String,
        page: Int,
        includeAdult: Boolean?,
        region: String?,
    ): MovieSearchEntity
}