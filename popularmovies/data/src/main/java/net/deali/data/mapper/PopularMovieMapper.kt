package net.deali.data.mapper

import net.deali.coredata.mapper.Mapper
import net.deali.coredata.util.resizeImage
import net.deali.coredomain.entity.MovieEntity
import net.deali.data.response.PopularMovieResponse
import net.deali.domain.model.PopularMovieEntity
import javax.inject.Inject

class PopularMovieMapper @Inject constructor() : Mapper<PopularMovieResponse, PopularMovieEntity> {
    override fun toModel(response: PopularMovieResponse): PopularMovieEntity = response.run {
        return PopularMovieEntity(
            totalPageCount = totalPages ?: 1,
            movieEntities = results?.filter {
                it.posterPath != null && it.overview != null
            }?.map {
                MovieEntity(
                    title = it.title ?: "",
                    isAdult = it.adult ?: false,
                    imageUrl = it.posterPath?.resizeImage(300) ?: "",
                    releaseDate = it.releaseDate ?: "",
                    overview = it.overview ?: ""
                )
            } ?: listOf()
        )
    }

}