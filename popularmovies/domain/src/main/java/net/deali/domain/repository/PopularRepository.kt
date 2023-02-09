package net.deali.domain.repository

import net.deali.domain.model.PopularMovieEntity
import net.deali.nativecore.Resource

interface PopularRepository {
    suspend fun getPopularMoives(
        page: Int
    ): Resource<PopularMovieEntity>
}