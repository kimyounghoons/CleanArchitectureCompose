package net.deali.coredomain.entity


data class MovieEntity(
    val id: Int = 0,
    val title: String = "",
    val imageUrl: String = "",
    val backdropUrl: String = "",
    val isAdult: Boolean = false,
    val releaseDate: String = "",
    val overview: String = ""
) : BaseEntity()