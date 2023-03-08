package net.deali.data.mapper

import net.deali.coredata.mapper.Mapper
import net.deali.data.response.MovieSearchKeywordResponse
import net.deali.domain.entity.MovieSearchKeywordEntity
import net.deali.domain.entity.SearchKeywordEntity
import javax.inject.Inject

class MovieSearchKeywordMapper @Inject constructor() :
    Mapper<MovieSearchKeywordResponse, MovieSearchKeywordEntity> {
    override fun toModel(response: MovieSearchKeywordResponse): MovieSearchKeywordEntity =
        response.run {
            MovieSearchKeywordEntity(
                totalPageCount = totalPages ?: 1,
                searchKeywordEntities = results?.map {
                    SearchKeywordEntity(
                        id = it.id ?: 0,
                        keyword = it.name ?: "",
                    )
                } ?: listOf(),
            )
        }
}