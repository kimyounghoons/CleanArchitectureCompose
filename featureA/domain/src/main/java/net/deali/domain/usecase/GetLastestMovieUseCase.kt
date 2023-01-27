package net.deali.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.deali.domain.model.PopularMovies
import net.deali.domain.repository.PopularRepository
import net.deali.nativecore.ApiError
import net.deali.nativecore.Resource
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLastestMovieUseCase @Inject constructor(
    private val repository: PopularRepository
) {
    operator fun invoke(): Flow<Resource<out PopularMovies>> = flow {
        emit(Resource.Loading())
        try {
            val result = repository.getPopularMoives()
            emit(Resource.Success(model = result))
        } catch (e: IOException) {
            emit(Resource.Fail(apiError = ApiError.NetworkError))
        } catch (e: Exception) {
            emit(Resource.Fail(apiError = ApiError.UnknownError))
        }
    }
}