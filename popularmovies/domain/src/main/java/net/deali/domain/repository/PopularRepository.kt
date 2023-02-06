package net.deali.domain.repository

import kotlinx.coroutines.flow.Flow
import net.deali.domain.model.PopularMovieEntity
import net.deali.nativecore.Resource

interface PopularRepository {
    fun getPopularMoives(
        page: Int
    ): Flow<Resource<PopularMovieEntity>>
}