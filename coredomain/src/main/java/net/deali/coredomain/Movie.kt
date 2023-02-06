package net.deali.coredomain

import net.deali.nativecore.BaseModel


data class Movie(
    val title: String = "",
    val imageUrl: String = "",
    val isAdult: Boolean = false,
    val releaseDate: String = "",
    val overview: String = ""
) : BaseModel()