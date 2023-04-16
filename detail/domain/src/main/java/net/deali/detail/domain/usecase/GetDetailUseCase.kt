package net.deali.detail.domain.usecase

import kotlinx.coroutines.flow.Flow
import net.deali.coredomain.BaseUseCase
import net.deali.coredomain.Resource
import net.deali.detail.domain.entity.DetailEntity
import net.deali.detail.domain.repository.DetailRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDetailUseCase @Inject constructor(
    private val repository: DetailRepository
) : BaseUseCase() {
    operator fun invoke(
        movieId: Int,
    ): Flow<Resource<DetailEntity>> = executeAndConvertToFlow {
        repository.getDetail(
            movieId = movieId
        )
    }

}