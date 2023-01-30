package net.deali.nativecore

sealed class ApiResponse {
    object NetworkError : ApiResponse()
    object UnknownError : ApiResponse()
    class HttpError(val code: Int? = null) : ApiResponse()
    object Success : ApiResponse()
}