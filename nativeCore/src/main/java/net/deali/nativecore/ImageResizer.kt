package net.deali.nativecore

fun String.resizeImage(width: Int? = null) = if (width == null) {
    "https://image.tmdb.org/t/p/original${this}"
} else {
    "https://image.tmdb.org/t/p/w${width}${this}"
}