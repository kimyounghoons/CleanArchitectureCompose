package net.deali.detail.domain.repository

import net.deali.coredomain.Resource
import net.deali.detail.domain.entity.DetailEntity

interface DetailRepository {
    suspend fun getDetail(
        movieId: Int,
    ): Resource<DetailEntity>
}