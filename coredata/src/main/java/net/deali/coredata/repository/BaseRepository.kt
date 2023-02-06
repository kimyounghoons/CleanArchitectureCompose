package net.deali.coredata.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.deali.coredomain.BaseEntity
import net.deali.nativecore.Resource
import net.deali.nativecore.exception.ApiException
import retrofit2.HttpException
import java.io.IOException

open class BaseRepository {
    fun <response : BaseEntity> callApi(responseFunction: suspend () -> response): Flow<Resource<response>> =
        flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(responseFunction.invoke()))
            } catch (e: HttpException) {
                emit(Resource.Fail(ApiException.HttpException(code = e.code())))
            } catch (e: IOException) {
                emit(Resource.Fail(ApiException.NetworkException))
            } catch (e: Exception) {
                emit(Resource.Fail(ApiException.UnknownException))
            }
        }
}
