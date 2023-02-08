package net.deali.domain.repository

import net.deali.domain.model.PopularMovieEntity

interface PopularRepository {
    suspend fun getPopularMoives(
        page: Int
    ): PopularMovieEntity
}