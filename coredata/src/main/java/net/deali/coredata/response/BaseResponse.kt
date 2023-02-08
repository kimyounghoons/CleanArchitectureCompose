package net.deali.coredata.response

import com.google.gson.annotations.SerializedName
import net.deali.nativecore.exception.ApiException

open class BaseResponse(
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("status_message")
    val statusMessage: String? = null,

    var apiException: ApiException? = null
) {
    fun isSuccess() = statusCode == null && apiException == null

    companion object {
        const val NETWORK_ERROR = 999
        const val UNKNOWN_ERROR = 9999
    }
}