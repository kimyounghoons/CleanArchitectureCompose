package net.deali.presentation.contentProvider

import net.deali.navigator.ActivityClassManager
import net.deali.navigator.NavigatorContentProvider
import net.deali.navigator.NavigatorKey
import net.deali.presentation.PopularMoviesActivity

class PopularMoviesContentProvider : NavigatorContentProvider() {
    override fun registerActivities() {
        ActivityClassManager.register(
            NavigatorKey.PopularMovies,
            PopularMoviesActivity::class.java
        )
    }
}