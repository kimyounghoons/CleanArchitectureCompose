package net.deali.data.mapper

import net.deali.coredata.response.BaseResponse
import net.deali.data.response.PopularMovieResponse
import net.deali.domain.model.PopularMovieEntity
import net.deali.nativecore.ApiResponse
import net.deali.nativecore.model.Movie
import net.deali.nativecore.resizeImage

fun PopularMovieResponse.toModel(): PopularMovieEntity {
    val entity = PopularMovieEntity(
        movies = results?.map {
            Movie(
                title = it.title ?: "",
                isAdult = it.adult ?: false,
                imageUrl = it.posterPath?.resizeImage(500) ?: "",
                releaseDate = it.releaseDate ?: "",
                overview = it.overview ?: ""
            )
        } ?: listOf()
    )
    if (isSuccess()) {
        entity.apiResponse = ApiResponse.Success
    } else {
        when (httpCode) {
            BaseResponse.NETWORK_ERROR -> {
                entity.apiResponse = ApiResponse.NetworkError
            }
            BaseResponse.UNKNOWN_ERROR -> {
                entity.apiResponse = ApiResponse.UnknownError
            }
            else -> {
                entity.apiResponse = ApiResponse.HttpError(code = httpCode)
            }
        }
    }
    return entity
}