package net.deali.nowplaying.data.service

import net.deali.nowplaying.data.response.NowPlayingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NowPlayingApiService {

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int,
        @Query("region") region: String?,
    ): NowPlayingResponse
}