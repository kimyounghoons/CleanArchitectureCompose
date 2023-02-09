package net.deali.coredomain.entity


data class MovieEntity(
    val title: String = "",
    val imageUrl: String = "",
    val isAdult: Boolean = false,
    val releaseDate: String = "",
    val overview: String = ""
) : BaseEntity()