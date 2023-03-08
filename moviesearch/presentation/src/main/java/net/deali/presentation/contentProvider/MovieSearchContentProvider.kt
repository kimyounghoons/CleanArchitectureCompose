package net.deali.presentation.contentProvider

import net.deali.navigator.ActivityClassManager
import net.deali.navigator.NavigatorContentProvider
import net.deali.navigator.NavigatorKey
import net.deali.presentation.searchResult.MovieSearchActivity

class MovieSearchContentProvider : NavigatorContentProvider() {
    override fun registerActivities() {
        ActivityClassManager.register(
            NavigatorKey.SearchResultMovies,
            MovieSearchActivity::class.java
        )
    }
}