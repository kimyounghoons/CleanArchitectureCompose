package net.deali.coredomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.deali.nativecore.Resource

open class BaseUseCase {
    fun <response : BaseEntity> callApi(responseFunction: suspend () -> response): Flow<Resource<response>> =
        flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(responseFunction.invoke()))
            } catch (e: Exception) {
                emit(Resource.Fail(e))
            }
        }
}