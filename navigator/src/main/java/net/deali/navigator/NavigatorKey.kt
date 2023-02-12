package net.deali.navigator

sealed class NavigatorKey {
    object MovieDetail : NavigatorKey() {
        const val KEY_MOVIE_ID = "KEY_MOVIE_ID"
        const val KEY_MOVIE_TITLE = "KEY_MOVIE_TITLE"
    }

    object PopularMovies : NavigatorKey()
    object SearchMovies : NavigatorKey()
    object NowPlayingMovies : NavigatorKey()
}