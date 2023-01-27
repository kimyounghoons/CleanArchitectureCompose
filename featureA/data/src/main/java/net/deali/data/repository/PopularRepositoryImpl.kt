package net.deali.data.repository

import net.deali.data.mapper.toModel
import net.deali.data.service.PopularApiService
import net.deali.domain.repository.PopularRepository
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val service: PopularApiService
) : PopularRepository {
    override suspend fun getPopularMoives() = service.getPopularMoives().toModel()
}