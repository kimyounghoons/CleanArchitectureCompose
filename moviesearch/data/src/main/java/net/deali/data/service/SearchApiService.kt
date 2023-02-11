package net.deali.data.service

import net.deali.data.response.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    /*
    * API 정보 : https://developers.themoviedb.org/3/search/search-movies
    * */
    @GET("/3/search/movie")
    suspend fun getMovieSearch(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean?,
        @Query("region") region: String?,
    ): MovieSearchResponse
}