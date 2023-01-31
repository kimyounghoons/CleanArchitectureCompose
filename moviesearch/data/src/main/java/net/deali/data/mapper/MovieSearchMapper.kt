package net.deali.data.mapper

import net.deali.coredata.response.BaseResponse
import net.deali.coredomain.Movie
import net.deali.data.response.MovieSearchResponse
import net.deali.domain.entity.MovieSearchEntity
import net.deali.nativecore.ApiResponse
import net.deali.nativecore.resizeImage

fun MovieSearchResponse.toModel(): MovieSearchEntity {
    val entity = MovieSearchEntity(
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
        entity.apply {
            apiResponse = ApiResponse.Success
            totalPageCount = totalPages ?: 1
        }
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