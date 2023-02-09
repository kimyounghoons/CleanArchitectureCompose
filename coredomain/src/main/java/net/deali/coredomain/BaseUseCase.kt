package net.deali.coredomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.deali.coredomain.entity.BaseEntity

open class BaseUseCase {
    fun <response : BaseEntity> callApi(
        responseFunction: suspend () -> Resource<response>
    ): Flow<Resource<response>> = flow {
        emit(Resource.Loading())
        emit(responseFunction.invoke())
    }
}
