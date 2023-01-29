package net.deali.domain.usecase

import kotlinx.coroutines.flow.Flow
import net.deali.domain.entity.MovieSearchEntity
import net.deali.domain.repository.SearchRepository
import net.deali.nativecore.BaseUseCase
import net.deali.nativecore.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieSearchUseCase @Inject constructor(
    private val repository: SearchRepository
) : BaseUseCase<MovieSearchEntity>() {
    operator fun invoke(
        query: String,
        page: Int,
        includeAdult: Boolean? = null,
        region: String? = null,
    ): Flow<Resource<MovieSearchEntity>> = callApi {
        repository.getSearchMovies(
            query,
            page,
            includeAdult,
            region
        )
    }
}