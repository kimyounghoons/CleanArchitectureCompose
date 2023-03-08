package net.deali.navigator

sealed class NavigatorKey {
    object MovieDetail : NavigatorKey() {
        const val KEY_MOVIE_ID = "KEY_MOVIE_ID"
        const val KEY_MOVIE_TITLE = "KEY_MOVIE_TITLE"
    }

    object PopularMovies : NavigatorKey()
    object SearchResultMovies : NavigatorKey() {
        const val KEY_KEYWORD = "KEY_KEYWORD"
    }

    object NowPlayingMovies : NavigatorKey()
}