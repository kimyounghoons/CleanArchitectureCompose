package net.deali.detail.data.service

import net.deali.detail.data.response.DetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApiService {

    /*
    * API 정보 : https://developers.themoviedb.org/3/movies/get-movie-details
    * */
    @GET("/3/movie/{movie_id}")
    suspend fun getNowPlayingMovies(
        @Path("movie_id") page: Int,
    ): DetailResponse
}