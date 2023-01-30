package net.deali.coredata.response

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("status_message")
    val statusMessage: String? = null,

    var httpCode: Int? = null,
) {
    fun isSuccess() = statusCode == null && httpCode == null

    companion object {
        const val NETWORK_ERROR = 999
        const val UNKNOWN_ERROR = 9999
    }
}