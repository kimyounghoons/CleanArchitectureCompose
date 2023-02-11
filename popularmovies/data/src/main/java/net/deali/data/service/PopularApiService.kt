package net.deali.data.service

import net.deali.data.response.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularApiService {

    /*
    * API 정보 : https://developers.themoviedb.org/3/movies/get-popular-movies
    * */
    @GET("/3/movie/popular")
    suspend fun getPopularMoives(
        @Query("page") page: Int,
    ): PopularMovieResponse
}