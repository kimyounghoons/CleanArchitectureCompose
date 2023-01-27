package net.deali.domain.repository

import net.deali.domain.model.PopularMovies

interface PopularRepository {
    suspend fun getPopularMoives(): PopularMovies
}