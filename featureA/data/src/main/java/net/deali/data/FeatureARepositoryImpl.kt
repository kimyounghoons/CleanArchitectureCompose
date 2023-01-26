package net.deali.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.deali.data.service.ApiService
import net.deali.domain.model.LastestMovie
import net.deali.domain.model.Resource
import net.deali.domain.repository.FeatureARepository
import retrofit2.HttpException
import javax.inject.Inject

class FeatureARepositoryImpl @Inject constructor(
    private val service: ApiService
) : FeatureARepository {
    override  fun getLatestMoives(): Flow<Resource<LastestMovie>> = flow {
        emit(Resource.Loading())
        try {
            val result = service.getLatestMoives()
            emit(Resource.Success(LastestMovie("test"))) //todo 수정 필요
        } catch (e: Exception) {
            if (e is HttpException) {
                emit(Resource.Fail(code = e.code(), exception = e))
            } else {
                emit(Resource.Fail(exception = e))
            }
        }
    }
}