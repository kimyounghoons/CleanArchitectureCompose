package net.deali.domain.usecase

import kotlinx.coroutines.flow.Flow
import net.deali.domain.model.PopularMovies
import net.deali.domain.repository.PopularRepository
import net.deali.nativecore.BaseUseCase
import net.deali.nativecore.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLastestMovieUseCase @Inject constructor(
    private val repository: PopularRepository
) : BaseUseCase<PopularMovies>() {
    operator fun invoke(): Flow<Resource<out PopularMovies>> = callApi {
        repository.getPopularMoives()
    }
}