package net.deali.coredomain

sealed class Resource<out T> {
    class Success<T>(val model: T) : Resource<T>()
    class Fail<T>(val exception: ApiException) : Resource<T>()
    class Loading<T> : Resource<T>()
}