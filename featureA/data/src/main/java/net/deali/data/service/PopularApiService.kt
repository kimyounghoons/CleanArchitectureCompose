package net.deali.data.service

import net.deali.data.dto.PopularMovieDTO
import retrofit2.http.GET

interface PopularApiService {
    @GET("/3/movie/popular")
    suspend fun getPopularMoives(): PopularMovieDTO
}