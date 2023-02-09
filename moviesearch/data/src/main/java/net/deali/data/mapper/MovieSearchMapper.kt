package net.deali.data.mapper

import net.deali.coredata.mapper.Mapper
import net.deali.coredata.util.resizeImage
import net.deali.coredomain.entity.MovieEntity
import net.deali.data.response.MovieSearchResponse
import net.deali.domain.entity.MovieSearchEntity
import javax.inject.Inject

class MovieSearchMapper @Inject constructor() : Mapper<MovieSearchResponse, MovieSearchEntity> {
    override fun toModel(response: MovieSearchResponse): MovieSearchEntity = response.run {
        MovieSearchEntity(
            totalPageCount = totalPages ?: 1,
            movieEntities = results?.map {
                MovieEntity(
                    title = it.title ?: "",
                    isAdult = it.adult ?: false,
                    imageUrl = it.posterPath?.resizeImage(500) ?: "",
                    releaseDate = it.releaseDate ?: "",
                    overview = it.overview ?: ""
                )
            } ?: listOf(),
        )
    }
}