package net.deali.detail.presentation.contentProvider

import net.deali.detail.presentation.MovieDetailActivity
import net.deali.navigator.ActivityClassManager
import net.deali.navigator.NavigatorContentProvider
import net.deali.navigator.NavigatorKey

class MovieDetailContentProvider : NavigatorContentProvider() {
    override fun registerActivities() {
        ActivityClassManager.register(NavigatorKey.MovieDetail, MovieDetailActivity::class.java)
    }
}