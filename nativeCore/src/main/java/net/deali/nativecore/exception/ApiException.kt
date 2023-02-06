package net.deali.nativecore.exception

sealed class ApiException {
    object NetworkException : ApiException()
    class HttpException(val code: Int) : ApiException()
    object UnknownException : ApiException()
}