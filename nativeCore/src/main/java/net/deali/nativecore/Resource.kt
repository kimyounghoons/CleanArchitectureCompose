package net.deali.nativecore

import net.deali.nativecore.exception.ApiException

sealed class Resource<T> {
    class Success<T>(val model: T) : Resource<T>()
    class Fail<T>(val exception: ApiException) : Resource<T>()
    class Loading<T> : Resource<T>()
}