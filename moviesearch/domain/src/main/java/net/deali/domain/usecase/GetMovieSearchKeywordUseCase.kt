package net.deali.domain.usecase

import kotlinx.coroutines.flow.Flow
import net.deali.coredomain.BaseUseCase
import net.deali.coredomain.Resource
import net.deali.domain.entity.MovieSearchKeywordEntity
import net.deali.domain.repository.SearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieSearchKeywordUseCase @Inject constructor(
    private val repository: SearchRepository
) : BaseUseCase() {
    operator fun invoke(
        query: String,
        page: Int,
    ): Flow<Resource<MovieSearchKeywordEntity>> = callApi {
        repository.getMovieSearchKeywords(
            query,
            page,
        )
    }
}