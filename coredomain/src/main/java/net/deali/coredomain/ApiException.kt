package net.deali.coredomain

sealed class ApiException {
    object NetworkException : ApiException()
    class HttpException(val code: Int) : ApiException()
    object UnknownException : ApiException()
}