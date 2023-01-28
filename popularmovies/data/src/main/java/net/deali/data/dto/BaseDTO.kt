package net.deali.data.dto

import com.google.gson.annotations.SerializedName

open class BaseDTO(
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("status_message")
    val statusMessage: String? = null
) {
    fun isSuccess() = statusCode == null
}