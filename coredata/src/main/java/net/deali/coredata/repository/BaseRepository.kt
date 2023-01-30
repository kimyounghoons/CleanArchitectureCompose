package net.deali.coredata.repository

import net.deali.coredata.response.BaseResponse
import net.deali.coredata.response.BaseResponse.Companion.NETWORK_ERROR
import net.deali.coredata.response.BaseResponse.Companion.UNKNOWN_ERROR
import retrofit2.HttpException
import java.io.IOException

open class BaseRepository {
    suspend inline fun <reified response : BaseResponse> safeResult(responseFunction: suspend () -> response): response {
        return try {
            responseFunction.invoke()
        } catch (e: HttpException) {
            val result = response::class.java.newInstance().apply {
                httpCode = e.code()
            }
            result
        } catch (e: IOException) {
            val result = response::class.java.newInstance().apply {
                httpCode = NETWORK_ERROR
            }
            result
        } catch (e: Exception) {
            val result = response::class.java.newInstance().apply {
                httpCode = UNKNOWN_ERROR
            }
            result
        }
    }
}
