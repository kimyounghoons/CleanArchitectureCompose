package net.deali.data.mapper

import net.deali.coredata.mapper.Mapper
import net.deali.coredomain.Movie
import net.deali.data.response.PopularMovieResponse
import net.deali.domain.model.PopularMovieEntity
import net.deali.nativecore.resizeImage
import javax.inject.Inject

class PopularMovieMapper @Inject constructor() : Mapper<PopularMovieResponse, PopularMovieEntity> {
    override fun toModel(response: PopularMovieResponse): PopularMovieEntity = response.run {
        return PopularMovieEntity(
            totalPageCount = totalPages ?: 1,
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
    }

}