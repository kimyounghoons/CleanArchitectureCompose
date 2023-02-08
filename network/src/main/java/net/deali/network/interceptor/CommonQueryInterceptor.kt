package net.deali.network.interceptor

import net.deali.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class CommonQueryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val httpUrl = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(QUERY_KEY_API, QUERY_VALUE_API)
            .addQueryParameter(QUERY_KEY_LANGUAGE, QUERY_VALUE_LANGUAGE)
            .build()

        val request = chain.request().newBuilder()
            .url(httpUrl)
            .build()

        return chain.proceed(request)
    }

    companion object {
        private const val QUERY_KEY_API = "api_key"
        private const val QUERY_KEY_LANGUAGE = "language"

        private const val QUERY_VALUE_API = BuildConfig.TMDB_API_KEY
        private const val QUERY_VALUE_LANGUAGE = "ko-KR"
    }
}