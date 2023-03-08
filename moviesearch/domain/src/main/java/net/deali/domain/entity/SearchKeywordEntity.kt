package net.deali.domain.entity

import net.deali.coredomain.entity.BaseEntity

data class SearchKeywordEntity(
    val id: Int = 0,
    val keyword: String = "",
) : BaseEntity()