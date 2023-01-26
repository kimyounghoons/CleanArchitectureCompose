package net.deali.data.service

import net.deali.data.dto.LatestDTO
import net.deali.data.dto.PopularDTO
import retrofit2.http.GET

interface ApiService {

    @GET("/3/movie/popular")
    suspend fun getPopularMoives(): PopularDTO

    @GET("/3/movie/latest")
    suspend fun getLatestMoives(): LatestDTO

}