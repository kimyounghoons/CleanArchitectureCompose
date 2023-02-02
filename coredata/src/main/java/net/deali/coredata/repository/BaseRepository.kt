package net.deali.coredata.repository

import net.deali.coredata.response.BaseResponse
import net.deali.coredomain.exception.CustomHttpException
import net.deali.coredomain.exception.NetworkException
import net.deali.coredomain.exception.UnknownException
import retrofit2.HttpException
import java.io.IOException

open class BaseRepository {
    suspend fun <response : BaseResponse> safeResult(responseFunction: suspend () -> response): response {
        try {
            return responseFunction.invoke()
        } catch (e: HttpException) {
            throw CustomHttpException(code = e.code())
        } catch (e: IOException) {
            throw NetworkException()
        } catch (e: Exception) {
            throw UnknownException()
        }
    }
}
