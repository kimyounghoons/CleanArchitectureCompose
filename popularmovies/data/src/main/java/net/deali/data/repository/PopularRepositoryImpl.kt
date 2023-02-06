package net.deali.data.repository

import net.deali.coredata.repository.BaseRepository
import net.deali.data.mapper.toModel
import net.deali.data.service.PopularApiService
import net.deali.domain.repository.PopularRepository
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val service: PopularApiService
) : BaseRepository(), PopularRepository {
    override fun getPopularMoives(page: Int) = callApi {
        service.getPopularMoives(page).toModel()
    }
}