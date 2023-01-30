package net.deali.nativecore

sealed class Resource<T> {
    class Success<T>(val model: T) : Resource<T>()
    class Fail<T>(val apiResponse: ApiResponse) : Resource<T>()
    class Loading<T> : Resource<T>()
}