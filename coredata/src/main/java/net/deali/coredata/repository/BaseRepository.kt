package net.deali.coredata.repository

import net.deali.coredata.response.BaseResponse
import net.deali.nativecore.exception.ApiException
import retrofit2.HttpException
import java.io.IOException

open class BaseRepository {
    inline fun <reified T : BaseResponse> safeResult(responseFunction: () -> T): T {
        return try {
            responseFunction.invoke()
        } catch (e: HttpException) {
            failResponse(ApiException.HttpException(code = e.code()))
        } catch (e: IOException) {
            failResponse(ApiException.NetworkException)
        } catch (e: Exception) {
            failResponse(ApiException.UnknownException)
        }
    }

    inline fun <reified T : BaseResponse> failResponse(
        exception: ApiException
    ): T = T::class.java.newInstance().apply {
        apiException = exception
    }
}
