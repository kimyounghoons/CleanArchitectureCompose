package net.deali.nativecore

import kotlinx.coroutines.flow.flow
import java.io.IOException

open class BaseUseCase<response> {
    fun callApi(responseFunction: suspend () -> response) = flow {
        emit(Resource.Loading())
        try {
            val result = responseFunction.invoke()
            emit(Resource.Success(model = result))
        } catch (e: IOException) {
            emit(Resource.Fail(apiError = ApiError.NetworkError))
        } catch (e: Exception) {
            emit(Resource.Fail(apiError = ApiError.UnknownError))
        }
    }
}