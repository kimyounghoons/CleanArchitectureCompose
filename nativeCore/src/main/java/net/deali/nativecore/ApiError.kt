package net.deali.nativecore

sealed class ApiError {
    object NetworkError : ApiError()
    object UnknownError : ApiError()
    class HttpError(val code: Int? = null) : ApiError()
    object None : ApiError()
}