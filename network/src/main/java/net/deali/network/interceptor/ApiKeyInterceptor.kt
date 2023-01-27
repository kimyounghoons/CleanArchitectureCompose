package net.deali.network.interceptor

import net.deali.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val httpUrl = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(API_KEY_NAME, BuildConfig.TMDB_API_KEY)
            .build()

        val request = chain.request().newBuilder()
            .url(httpUrl)
            .build()

        return chain.proceed(request)
    }

    companion object {
        private const val API_KEY_NAME = "api_key"
    }
}