package net.deali.coredomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.deali.nativecore.Resource

open class BaseUseCase {
    fun <response : BaseEntity> callApi(
        responseFunction: suspend () -> response
    ): Flow<Resource<response>> = flow {
        emit(Resource.Loading())
        val entity = responseFunction.invoke()
        val apiException = entity.apiException
        if (apiException == null) {
            emit(Resource.Success(entity))
        } else {
            emit(Resource.Fail(apiException))
        }
    }
}
