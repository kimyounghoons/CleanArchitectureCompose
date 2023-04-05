package net.deali.data.mapper

import net.deali.data.response.MovieSearchKeywordResponse
import net.deali.domain.entity.MovieSearchKeywordEntity
import net.deali.domain.entity.SearchKeywordEntity

fun MovieSearchKeywordResponse.toDomainModel() = MovieSearchKeywordEntity(
    totalPageCount = totalPages ?: 1,
    searchKeywordEntities = results?.map {
        SearchKeywordEntity(
            id = it.id ?: 0,
            keyword = it.name ?: "",
        )
    } ?: listOf(),
)