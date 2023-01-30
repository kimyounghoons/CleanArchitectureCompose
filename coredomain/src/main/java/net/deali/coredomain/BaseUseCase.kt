package net.deali.coredomain

import kotlinx.coroutines.flow.flow
import net.deali.nativecore.ApiResponse
import net.deali.nativecore.Resource

open class BaseUseCase {
    fun <response : BaseEntity>  callApi(responseFunction: suspend () -> response) = flow {
        emit(Resource.Loading())
        val result = responseFunction.invoke()
        when (result.apiResponse) {
            ApiResponse.Success -> {
                emit(Resource.Success(model = result))
            }
            else -> {
                emit(Resource.Fail(apiResponse = result.apiResponse))
            }
        }
    }
}