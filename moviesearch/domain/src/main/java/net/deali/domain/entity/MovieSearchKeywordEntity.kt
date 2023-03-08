package net.deali.domain.entity

import net.deali.coredomain.entity.BaseEntity

data class MovieSearchKeywordEntity(
    var totalPageCount: Int = 1,
    val searchKeywordEntities: List<SearchKeywordEntity> = listOf(),
) : BaseEntity()