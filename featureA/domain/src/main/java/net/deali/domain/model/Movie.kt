package net.deali.domain.model

data class Movie(
    val title: String = "",
    val imageUrl: String = "",
    val isAdult: Boolean = false,
    val releaseDate: String = "",
    val overview: String = ""
)