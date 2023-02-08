package net.deali.domain.usecase

import kotlinx.coroutines.flow.Flow
import net.deali.coredomain.BaseUseCase
import net.deali.domain.model.PopularMovieEntity
import net.deali.domain.repository.PopularRepository
import net.deali.nativecore.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPopularMovieUseCase @Inject constructor(
    private val repository: PopularRepository
) : BaseUseCase() {
    operator fun invoke(page: Int): Flow<Resource<out PopularMovieEntity>> = callApi {
        repository.getPopularMoives(page)
    }

}