package net.deali.domain.model

sealed class Resource<T> {
    class Success<T>(val model: T) : Resource<T>()
    class Fail<T>(val code: Int? = null, val exception: Throwable? = null) : Resource<T>()
    class Loading<T> : Resource<T>()
}