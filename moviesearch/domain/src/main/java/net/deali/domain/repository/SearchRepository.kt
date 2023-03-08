package net.deali.domain.repository

import net.deali.coredomain.Resource
import net.deali.domain.entity.MovieSearchEntity
import net.deali.domain.entity.MovieSearchKeywordEntity

interface SearchRepository {
    suspend fun getSearchMovies(
        query: String,
        page: Int,
        includeAdult: Boolean?,
        region: String?,
    ): Resource<MovieSearchEntity>

    suspend fun getMovieSearchKeywords(
        query: String,
        page: Int,
    ): Resource<MovieSearchKeywordEntity>
}