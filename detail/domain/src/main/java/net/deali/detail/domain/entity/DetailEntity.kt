package net.deali.detail.domain.entity

import net.deali.coredomain.entity.BaseEntity

data class DetailEntity(
    val backdropUrl: String = "",
    val imageUrl: String = "",
    val popularity: Double = 0.0,
    val productionCompanies: List<ProductionCompany> = listOf(),
    val voteAverage: Double = 0.0,
    val voteCount: Float = 0f,
    val overView: String = "",
    val budget: Int = 0,
) : BaseEntity() {
    data class ProductionCompany(
        val id: Int = 0,
        val imageUrl: String = "",
        val name: String = ""
    )
}